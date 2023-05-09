package com.login.LOGIN.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.LOGIN.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findUserByUserEmail(String email);
	
	Boolean existsByUserEmail(String email);
}
