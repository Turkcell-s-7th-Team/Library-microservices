package com.TurkcellTakim7.publisher_service.domain.exceptions;

public class PublisherWithGivenNameIsAlreadyExistException extends RuntimeException {

 
    public PublisherWithGivenNameIsAlreadyExistException(String publisherName) {
        super("Publisher with name '" + publisherName + "' already exists.");
    }
    public PublisherWithGivenNameIsAlreadyExistException() {
        super("Publisher with given name already exists.");
    }
}