package com.layermark.library.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String resource, Integer id) {
        super(resource + "with id - " + id + " not found");
    }
}
