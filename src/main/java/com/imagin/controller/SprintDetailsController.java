package com.imagin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.imagin.entity.Users;
import com.imagin.model.Iteration;
import com.imagin.model.SprintDetails;
import com.imagin.model.SprintDetailsResponse;
import com.imagin.repository.IterationRepository;
import com.imagin.service.SprintDetailsService;
import com.imagin.service.impl.ExcelGenerator;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SprintDetailsController {
	
	@Autowired
	SprintDetailsService sprintDetailsService;
	
	@Autowired
	IterationRepository iterationRepository;
	
	@GetMapping("/getCurrentWorkitems/{iterationId}")
	SprintDetailsResponse getCurrentWorkitems(@PathVariable String iterationId) {
		return sprintDetailsService.getWorkitemsByIterationId(iterationId);
	}
	
	@GetMapping("/getIterations/{teamName}")
	List<Iteration> getIterations(@PathVariable String teamName) {
		return sprintDetailsService.getIterations(teamName);
	}
	
	@GetMapping("/getAllTeams")
	List<String> getAllTeams() {
		return iterationRepository.findDistinctTeamName();
	}
	
	@GetMapping("/sprint/export/excel/{iterationId}")
    public void exportToExcel(@PathVariable String iterationId, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sprint_" + iterationId + ".xls";
        response.setHeader(headerKey, headerValue);
        List<SprintDetails> sprintDetails = sprintDetailsService.getWorkitemsByIterationId(iterationId).getSprintDetails();
        ExcelGenerator excelExporter = new ExcelGenerator(sprintDetails);
        excelExporter.generateExcelFile(response);    
    }
	
	@GetMapping("/getCurrentWorkitems/{teamName}/start/{startDate}/end/{endDate}")
	List<SprintDetailsResponse> getCurrentWorkitemsByStartAndEndDate(@PathVariable String teamName, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date endDate) {
		return sprintDetailsService.getWorkitemsByStartAndEndDate(teamName, startDate, endDate);
	}
	
	@GetMapping("/getUsersByTeamName/{teamName}")
	List<Users> getUsersByTeamName(@PathVariable String teamName) {
		return sprintDetailsService.getUsersByTeamName(teamName);
	}
	
	@PatchMapping("/updateUser/{userId}")
	Users updateUserById(@PathVariable int userId) {
		return sprintDetailsService.updateUserById(userId);
	}

}
