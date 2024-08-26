package com.example.postmanagement.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.postmanagement.DTO.PostDTO;

public interface PostManagementService {

	List<PostDTO> list();
	
	PostDTO findById(String id);

	boolean add(PostDTO post);

	boolean edit(String id,PostDTO post);

	boolean delete(String id);
}
