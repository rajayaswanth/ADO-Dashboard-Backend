package com.imagin.service;

import java.util.Date;
import java.util.List;

import com.imagin.entity.Users;
import com.imagin.model.Iteration;
import com.imagin.model.SprintDetailsResponse;

public interface SprintDetailsService {
	
	SprintDetailsResponse getWorkitemsByIterationId(String iterationId);
	
	List<SprintDetailsResponse> getWorkitemsByStartAndEndDate(String teamName, Date start, Date end);
	
	List<Iteration> getIterations(String teamName);
	
	List<Iteration> getLastIterations(String teamName, int count);
	
	List<Users> getUsersByTeamName(String teamname);
	
	Users updateUserById(int userId);

}
