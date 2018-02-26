package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.example.demo.bean.RegisterDTO;

@Component
public interface JPARepository extends JpaRepository<RegisterDTO, String> {
	public RegisterDTO getByName(String s);
	public void delete(RegisterDTO s);
	
}
