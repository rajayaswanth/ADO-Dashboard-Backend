package com.imagin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imagin.entity.Iterations;

public interface IterationRepository extends JpaRepository<Iterations, Integer> {
	
	Iterations findByTimeFrame(String timeFrame);
	
	Iterations findByIterationId(String iterationId);
	
	List<Iterations> findAllByTeamName(String teamName);
	
	@Query(value = "SELECT * FROM dashboard.iterations i where i.start_date >= :startDate AND i.end_date <= :endDate AND i.team_name = :teamName AND i.time_frame != 'future';", nativeQuery = true)
	List<Iterations> findAllByTeamNameAndDate(String teamName, Date startDate, Date endDate);
	
	@Query("SELECT DISTINCT a.teamName FROM Iterations a")
	List<String> findDistinctTeamName();

}
