package com.imagin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.imagin.entity.Iterations;
import com.imagin.entity.WorkItems;
import com.imagin.model.Iteration;
import com.imagin.model.IterationResponse;
import com.imagin.model.IterationWorkItems;
import com.imagin.model.PrDetails;
import com.imagin.model.WorkItemFields;
import com.imagin.model.WorkItemRelationDetails;
import com.imagin.model.WorkItemRelations;
import com.imagin.repository.IterationRepository;
import com.imagin.repository.WorkItemsRepository;
import com.imagin.service.DataImportService;

@Service
public class DataImportServiceImpl implements DataImportService {
	
	@Autowired
	@Qualifier("orgBaseClient")
	WebClient orgBaseClient;
	
	@Autowired
	WebClient webClient;
	
	@Value("${ado.token}")
	private String token;
	
	@Autowired
	IterationRepository iterationRepository;
	
	@Autowired
	WorkItemsRepository workItemsRepository;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a", Locale.ENGLISH);

	@Override
	public IterationResponse importIterations(String teamName) {
		IterationResponse iterationsResponse = orgBaseClient.get()
				.uri(teamName + "/_apis/work/teamsettings/iterations?api-version=7.0")
				.headers(h -> h.setBasicAuth("", token))
				.retrieve()
				.bodyToMono(IterationResponse.class)
				.block();
		try {
			saveItrations(iterationsResponse, teamName);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return iterationsResponse;
	}
	
	private void saveItrations(IterationResponse iterationResponse, String teamName) throws ParseException {
		List<Iterations> iterationsList = new ArrayList<>();
		for (Iteration iteration : iterationResponse.getValue()) {
			Iterations existingIteration = iterationRepository.findByIterationId(iteration.getIterationId());
			Iterations iterations = new Iterations();
			if(existingIteration != null) {
				if(iteration.getAttributes().getTimeFrame() != existingIteration.getTimeFrame()) {
					iterations.setId(existingIteration.getId());
				}
			}
			iterations.setIterationId(iteration.getIterationId());
			iterations.setIterationUrl(iteration.getIterationURL());
			iterations.setPath(iteration.getIterationPath());
			iterations.setName(iteration.getName());
			iterations.setTeamName(teamName);
			iterations.setStartDate(convertStringToDate(iteration.getAttributes().getStartDate()));
			iterations.setEndDate(convertStringToDate(iteration.getAttributes().getEndDate()));
			iterations.setTimeFrame(iteration.getAttributes().getTimeFrame());
			iterations.setWorkItemsLink(getWorkitemslink(iteration));
			iterationsList.add(iterations);
		}
		iterationRepository.saveAll(iterationsList);
	}
	
	private String getWorkitemslink(Iteration iteration) {
		Iteration iterationsResponse = webClient.get()
				.uri(iteration.getIterationURL())
				.headers(h -> h.setBasicAuth("", token))
				.retrieve()
				.bodyToMono(Iteration.class)
				.block();
		return iterationsResponse.getLinks().getWorkitems().getWorkItemsLink();
	}
	
	private Date convertStringToDate(String date) {
		date = date.replace('Z',' ');
		LocalDateTime ldt = LocalDateTime.parse(date.trim());
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public void importSprintWorkItems(String iterationId) {
		Iterations iterationDetails = iterationRepository.findByIterationId(iterationId);
		List<WorkItems> userStories = new ArrayList<>();
		IterationWorkItems workItemsList = webClient.get()
				.uri(iterationDetails.getWorkItemsLink())
				.headers(h -> h.setBasicAuth("", token))
				.retrieve()
				.bodyToMono(IterationWorkItems.class)
				.block();
		for (WorkItemRelations workItemRelations : workItemsList.getWorkItemRelations()) {
			String workItemUrl = "";
			if(workItemRelations.getSource() != null)
				workItemUrl = workItemRelations.getSource().getUrl();
			if(workItemRelations.getTarget() != null)
				workItemUrl = workItemRelations.getTarget().getUrl();
			
			WorkItemFields workItemDetails = webClient.get()
					.uri(workItemUrl + "?$expand=relations")
					.headers(h -> h.setBasicAuth("", token))
					.retrieve()
					.bodyToMono(WorkItemFields.class)
					.block();
			
			WorkItems workItems = new WorkItems();
			if(workItemDetails.getRelations() != null) {
				for (WorkItemRelationDetails workItems2 : workItemDetails.getRelations()) {
					if(workItems2.getAttributes().getName().equals("Pull Request")) {
						String prUrl = workItems2.getUrl();
						String[] urlChunk = prUrl.split("%2F");
						String prNumber = urlChunk[urlChunk.length-1];
						workItems.setPrNumber(prNumber);
						workItems.setPrCreatedDate(workItems2.getAttributes().getPrCreatedDate());
						PrDetails prDetails = orgBaseClient.get()
								.uri("/_apis/git/pullrequests/" + prNumber + "?api-version=7.2-preview.1")
								.headers(h -> h.setBasicAuth("", token))
								.retrieve()
								.bodyToMono(PrDetails.class)
								.block();
						if(prDetails != null) {
							workItems.setPrStatus(prDetails.getStatus());
							if(prDetails.isDraft())
								workItems.setPrType("Draft");
							else
								workItems.setPrType("Active");
						}
					}
				}
			}
			workItems.setIteration(iterationDetails.getName());
			workItems.setIterationId(iterationDetails.getIterationId());
			workItems.setAreaPath(workItemDetails.getFields().getAreaPath());
			if(workItemDetails.getFields().getAssignedTo() != null) {
				workItems.setAssignedTo(workItemDetails.getFields().getAssignedTo().getDisplayName());
				workItems.setAssignedEmail(workItemDetails.getFields().getAssignedTo().getEmail());
			}
			workItems.setDevelopmentCompletedDate(workItemDetails.getFields().getDevelopmentCompletedDate());
			workItems.setDevStartDate(workItemDetails.getFields().getDevStartDate());
			workItems.setIterationPath(workItemDetails.getFields().getIterationPath());
			workItems.setQaCompletedDate(workItemDetails.getFields().getQaCompletedDate());
			workItems.setState(workItemDetails.getFields().getState());
			workItems.setStoryPoints(workItemDetails.getFields().getStoryPoints());
			workItems.setTitle(workItemDetails.getFields().getTitle());
			if(workItemDetails.getFields().getWorkItemType().equalsIgnoreCase("Task") && workItemRelations.getSource() != null) {
				workItems.setParentWorkItemNumber(workItemRelations.getSource().getId());
			}
			workItems.setWorkItemNumber(workItemDetails.getUserStoryNumber());
			workItems.setWorkItemType(workItemDetails.getFields().getWorkItemType());
			List<WorkItems> existingStories = workItemsRepository.findByWorkItemNumber(workItemDetails.getUserStoryNumber());
			WorkItems existingStory = null;
			if(existingStories.size() == 1)
				existingStory = existingStories.get(0);
			else if(existingStories.size() > 1) {
				existingStory = existingStories.stream().max(Comparator.comparing(WorkItems::getSpillOverCount)).get();
				workItems.setSpillOverCount(existingStory.getSpillOverCount());
			}
			if(existingStory != null && !existingStory.getIteration().equals(iterationDetails.getName())) {
				workItems.setSpillOverCount(existingStory.getSpillOverCount() + 1);
			}
			if(existingStory != null && existingStory.getIteration().equals(iterationDetails.getName())) {
				workItems.setId(existingStory.getId());
			}
			if(existingStory == null) {
				workItems.setSpillOverCount(0);
			}
			userStories.add(workItems);
		}
		workItemsRepository.saveAll(userStories);
		cleanUnwantedStories(userStories, iterationDetails.getIterationId());
	}
	
	private void cleanUnwantedStories(List<WorkItems> userStories, String iterationId) {
		List<WorkItems> existingStories = workItemsRepository.findByIterationId(iterationId);
		List<String> existingStoriesIds = existingStories.stream().map(f -> f.getWorkItemNumber()).collect(Collectors.toList());
		List<String> userStoriesIds = userStories.stream().map(f -> f.getWorkItemNumber()).collect(Collectors.toList());
		existingStoriesIds.removeAll(userStoriesIds);
		workItemsRepository.deleteAllByWorkItemNumberIn(existingStoriesIds);
	}

}
