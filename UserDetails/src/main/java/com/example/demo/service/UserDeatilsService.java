package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.RegisterDTO;

public interface UserDeatilsService {
	
	public RegisterDTO registerUser(RegisterDTO user);

	public boolean login(String name, String pass);

	public List<RegisterDTO> getDetails();

	public RegisterDTO update(RegisterDTO name);

	public void delete(RegisterDTO name);
	

}
