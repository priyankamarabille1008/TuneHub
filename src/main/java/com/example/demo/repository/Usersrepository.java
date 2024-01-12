package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface Usersrepository extends JpaRepository<Users,Integer>{
	public Users findByEmail(String email);
}
