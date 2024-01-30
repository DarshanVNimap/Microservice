package com.ratingservice.exception;

public class ResourceNotFoundExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3756600517681175987L;

	public ResourceNotFoundExeption() {
		super("Resource not found!");
		// TODO Auto-generated constructor stub
	}

	public ResourceNotFoundExeption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
