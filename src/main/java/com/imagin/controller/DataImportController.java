package com.imagin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.imagin.model.IterationResponse;
import com.imagin.service.DataImportService;

@RestController
public class DataImportController {

	@Autowired
	DataImportService dashboardService;

	@GetMapping("/importItrations/{teamName}")
	public IterationResponse importIterations(@PathVariable String teamName) {
		return dashboardService.importIterations(teamName);
	}

	@GetMapping("/importCurrentWorkItems/{iterationId}")
	public void importCurrentSprintWorkItems(@PathVariable String iterationId) {
		dashboardService.importSprintWorkItems(iterationId);
	}

}
