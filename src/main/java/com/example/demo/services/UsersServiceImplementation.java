package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.Usersrepository;

@Service
public class UsersServiceImplementation implements UsersService{

	@Autowired
	Usersrepository repo;
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "user added succesfully";
	}
	
	@Override
	public boolean emailExists(String email) {
		
		if(repo.findByEmail(email)==null) {
			return false;
		}else {
			return true;
		}
	}
	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);
		String pass=user.getPassword();
		String role=user.getRole();
		if(password.equals(pass)) {
		return true;
		}else {
			return false;
		}
	}
	@Override
	public String getuserRole(String email) {
		Users user=repo.findByEmail(email);
		String role=user.getRole();
		return role;
	}

	@Override
	public Users getUser(String email) {
		return repo.findByEmail(email);
		
	}

	@Override
	public void updateUser(Users user) {
		repo.save(user);
	}

	@Override
	public boolean isPremiumUser(String email) {
		System.out.println(email);
		Users user=repo.findByEmail(email);
	    System.out.println(user);
	    if (user != null) {
	        return user.isPremium();
	    } else {
	        return false;
	    }
	}


	

}
