package com.example.postmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postmanagement.DTO.PostDTO;
import com.example.postmanagement.Exceptions.PostNotFoundException;
import com.example.postmanagement.DTO.PostDTO;
import com.example.postmanagement.service.PostManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/post")
@CrossOrigin("http://localhost:3000")
public class PostController {

	@Autowired
	PostManagementService service;

	@GetMapping("/find/{id}")
	public ResponseEntity find(@PathVariable("id") String id) {

		if (id == null || id.equals("") || service.findById(id) == null ) {
			throw new PostNotFoundException(id);
		}
		
		return new ResponseEntity(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity list() {
		return new ResponseEntity(service.list(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity add(@RequestBody PostDTO post) {

		return new ResponseEntity(service.add(post), HttpStatus.OK);
	}

	@PutMapping("update/{id}")
	public ResponseEntity edit(@PathVariable("id") String id, @RequestBody PostDTO post) {
		// TODO: process PUT request

		if (id == null || id.equals("") || service.findById(id) == null ) {
			throw new PostNotFoundException(id);
		}
		return new ResponseEntity(service.edit(id, post), HttpStatus.OK);
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity delete(@PathVariable("id") String id) {
		// TODO: process PUT request
		
		if (id == null || id.equals("") || service.findById(id) == null ) {
			throw new PostNotFoundException(id);
		}

		return new ResponseEntity(service.delete(id), HttpStatus.OK);
	}

}
