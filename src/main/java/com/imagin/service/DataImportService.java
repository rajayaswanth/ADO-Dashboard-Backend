package com.imagin.service;

import com.imagin.model.IterationResponse;

public interface DataImportService {
	
	IterationResponse importIterations(String teamName);
	
	void importSprintWorkItems(String iterationId);

}
