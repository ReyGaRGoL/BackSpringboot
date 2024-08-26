package com.example.postmanagement.Exceptions;

public class PostNotFoundException extends RuntimeException {

	public PostNotFoundException(String id) {
		super("no se encontro un post con id: " + id);
	}
	
}
