package com.layermark.library.api.dto;

import com.layermark.library.repository.entity.BookEntity;
import java.util.List;

public class AuthorDto {
    private Integer id;
    private String name;
    private List<BookEntity> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
