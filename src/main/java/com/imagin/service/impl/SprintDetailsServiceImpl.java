package com.imagin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagin.entity.Iterations;
import com.imagin.entity.Users;
import com.imagin.entity.WorkItems;
import com.imagin.model.Iteration;
import com.imagin.model.IterationAttributes;
import com.imagin.model.SprintDetails;
import com.imagin.model.SprintDetailsResponse;
import com.imagin.repository.IterationRepository;
import com.imagin.repository.UserRepository;
import com.imagin.repository.WorkItemsRepository;
import com.imagin.service.SprintDetailsService;

@Service
public class SprintDetailsServiceImpl implements SprintDetailsService {
	
	@Autowired
	IterationRepository iterationRepository;
	
	@Autowired
	WorkItemsRepository workItemsRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public SprintDetailsResponse getWorkitemsByIterationId(String iterationId) {
		Iterations iterationDetails = iterationRepository.findByIterationId(iterationId);
		SprintDetailsResponse response = new SprintDetailsResponse();
		List<SprintDetails> sprintDetails = new ArrayList<>();
		int sprintCommitPoints = 0;
		int sprintCompletedPoints = 0;
		int sprintCompletionPercentage = 0;
		if(iterationDetails != null) {
			List<WorkItems> workItemDetails = workItemsRepository.findByIterationId(iterationDetails.getIterationId());
			for (WorkItems workItems : workItemDetails) {
					if(workItems.getWorkItemType().equalsIgnoreCase("User Story") || workItems.getWorkItemType().equalsIgnoreCase("Bug")) {
						Users user = userRepository.findByName(workItems.getAssignedTo());
						if(user != null && user.isGrc()) {
							SprintDetails workItem = new SprintDetails();
							workItem.setAssignedTo(workItems.getAssignedTo());
							workItem.setSoCount(workItems.getSpillOverCount());
							workItem.setState(workItems.getState());
							workItem.setStoryPoints(workItems.getStoryPoints());
							workItem.setTitle(workItems.getTitle());
							workItem.setUserStoryNumber(workItems.getWorkItemNumber());
							workItem.setDevCompletedDate(workItems.getDevelopmentCompletedDate());
							workItem.setDevStartDate(workItems.getDevStartDate());
							workItem.setQaCompletedDate(workItems.getQaCompletedDate());
							workItem.setPrCreatedDate(workItems.getPrCreatedDate());
							workItem.setPrType(workItems.getPrType());
							workItem.setPrStatus(workItems.getPrStatus());
							workItem.setType(workItems.getWorkItemType());
							if(workItems.getState().equals("QA Completed") || workItems.getState().equals("Closed"))
								sprintCompletedPoints += workItems.getStoryPoints();
							int completionPercentage = getCompletionPercentage(workItems.getState(), workItems.getPrCreatedDate());
							sprintCompletionPercentage += completionPercentage;
							workItem.setCompletionPercentage(completionPercentage);
							workItem.setExpand(false);
							sprintCommitPoints += workItems.getStoryPoints();
							sprintDetails.add(workItem);
						}
					}
			}
		}
		response.setSprintDetails(sprintDetails);
		if(sprintDetails.size() > 0)
			response.setSprintCompletePercentage(Math.round(sprintCompletionPercentage/sprintDetails.size()));
		response.setUserStoriesCount(sprintDetails.size());
		response.setSprintCommitPoints(sprintCommitPoints);
		response.setCompletedPoints(sprintCompletedPoints);
		response.setSprintName(iterationDetails.getName());
		return response;
	}
	
	private int getCompletionPercentage(String storyStatus, Date prCreatedDate) {
		int completionPercent = 0;
		if(prCreatedDate != null)
			completionPercent = 25;
		if(storyStatus.equals("Development Completed"))
			completionPercent = 50;
		if(storyStatus.equals("In QA"))
			completionPercent = 75;
		if(storyStatus.equals("QA Completed") || storyStatus.equals("Closed"))
			completionPercent = 100;
		return completionPercent;
	}

	@Override
	public List<Iteration> getIterations(String teamName) {
		List<Iterations> iterations = iterationRepository.findAllByTeamName(teamName);
		List<Iteration> response = new ArrayList<>();
		for (Iterations iteration : iterations) {
			Iteration i = new Iteration();
			i.setIterationId(iteration.getIterationId());
			i.setName(iteration.getName());
			IterationAttributes att = new IterationAttributes();
			att.setStartDate(iteration.getStartDate().toString());
			att.setEndDate(iteration.getEndDate().toString());
			att.setTimeFrame(iteration.getTimeFrame());
			i.setAttributes(att);
			response.add(i);
		}
		return response;
	}

	@Override
	public List<Iteration> getLastIterations(String teamName, int count) {
		return null;
	}

	@Override
	public List<SprintDetailsResponse> getWorkitemsByStartAndEndDate(String teamName, Date start, Date end) {
		Map<String, List<SprintDetails>> dataByUser = new HashMap<>();
		List<SprintDetailsResponse> response = new ArrayList<>();
		List<Iterations> iterations = iterationRepository.findAllByTeamNameAndDate(teamName, start, end);
		for (Iterations iteration : iterations) {
			SprintDetailsResponse sprintDetails = getWorkitemsByIterationId(iteration.getIterationId());
			Map<String, List<SprintDetails>> groupedData = sprintDetails.getSprintDetails().stream().filter(f -> f.getAssignedTo() != null).collect(Collectors.groupingBy(s -> s.getAssignedTo()));
			groupedData.keySet().forEach(key -> {
				if(dataByUser.containsKey(key)) {
					List<SprintDetails> existingData = dataByUser.get(key);
					existingData.addAll(groupedData.get(key));
				}
				dataByUser.put(key, groupedData.get(key));
			});
			sprintDetails.setUserdata(dataByUser);
			response.add(sprintDetails);
		}
		return response;
	}

	@Override
	public List<Users> getUsersByTeamName(String teamname) {
		List<Users> users = userRepository.findAllByTeamNameOrderByGrcDescNameAsc(teamname);
		return users;
	}

	@Override
	public Users updateUserById(int userId) {
		Optional<Users> user = userRepository.findById(userId);
		if(user.isPresent()) {
			user.get().setGrc(!user.get().isGrc());
			user = Optional.of(userRepository.save(user.get()));
		}
		return user.get();
	}

}
