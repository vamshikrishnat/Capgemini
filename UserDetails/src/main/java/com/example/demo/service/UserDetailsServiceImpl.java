package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.bean.RegisterDTO;
import com.example.demo.repo.JPARepository;

@Component
public class UserDetailsServiceImpl implements UserDeatilsService {

	@Autowired
	JPARepository repo;

	@Override
	public RegisterDTO registerUser(RegisterDTO user) {
		return repo.save(user);

	}

	@Override
	public boolean login(String name, String pass) {
		RegisterDTO dto = repo.getByName(name);
		if ((dto.getName().equals(name)) && (dto.getPassword().equals(pass)))
			return true;
		else
			return false;
	}

	@Override
	public List<RegisterDTO> getDetails() {
		List<RegisterDTO> list = repo.findAll();
		return list;
	}

	@Override
	public RegisterDTO update(RegisterDTO user) {
		RegisterDTO user1=repo.save(user);
		return user1;
	}

	@Override
	public void delete(RegisterDTO name) {
		
		repo.delete(name);
	}

}
