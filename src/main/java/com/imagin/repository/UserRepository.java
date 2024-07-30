package com.imagin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imagin.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Users findByEmail(String email);
	
	Users findByName(String name);
	
	List<Users> findAllByTeamNameOrderByGrcDescNameAsc(String teamName);

}
