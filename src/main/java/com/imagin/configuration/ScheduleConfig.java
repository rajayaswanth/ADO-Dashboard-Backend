package com.imagin.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.imagin.model.Iteration;
import com.imagin.model.IterationResponse;
import com.imagin.repository.IterationRepository;
import com.imagin.service.DataImportService;
import com.imagin.service.SprintDetailsService;

@Configuration
@EnableScheduling
public class ScheduleConfig {
	
	@Autowired
	IterationRepository iterationRepository;
	
	@Autowired
	DataImportService dashboardService;
	
	@Autowired
	SprintDetailsService sprintDetailsService;
	
	@Scheduled(cron = "0 0 8 * * *")
	public void getIterations() {
		List<String> teams = iterationRepository.findDistinctTeamName();
		for (String team : teams) {
			dashboardService.importIterations(team);
		}
	}
	
	@Scheduled(cron = "0 0 */1 * * *")
	public void getADOData() {
		System.out.println("Getting ADO data");
		List<String> teams = iterationRepository.findDistinctTeamName();
		for (String team : teams) {
			System.out.println("Getting data for team - " + team);
			IterationResponse iterations = dashboardService.importIterations(team);
			for (Iteration iteration : iterations.getValue()) {
				if(iteration.getAttributes().getTimeFrame().equalsIgnoreCase("current")) {
					System.out.println("Getting data for iteration - " + iteration.getIterationId());
					dashboardService.importSprintWorkItems(iteration.getIterationId());
				}
			}
		}
	}

}
