package com.imagin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagin.entity.WorkItems;

import jakarta.transaction.Transactional;

public interface WorkItemsRepository extends JpaRepository<WorkItems, Integer> {
	
	List<WorkItems> findByWorkItemNumber(String workItemNumber);
	
	List<WorkItems> findByIterationId(String iterationId);
	
	@Transactional
	void deleteAllByWorkItemNumberIn(List<String> workItemNumbers);

}
