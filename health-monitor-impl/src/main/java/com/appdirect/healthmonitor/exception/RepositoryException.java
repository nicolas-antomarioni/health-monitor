package com.appdirect.healthmonitor.exception;

/**
 * This exception should be thrown when a repository operation error occurs
 */
public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = 3134719459492770052L;

	public RepositoryException(String message) {
		super(message);
	}
}
