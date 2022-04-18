package com.layermark.library.service;

import com.layermark.library.exception.NotFoundException;
import com.layermark.library.repository.AuthorRepository;
import com.layermark.library.repository.entity.AuthorEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public AuthorEntity getAuthorById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("author", id));
    }

    public AuthorEntity saveAuthor(AuthorEntity author) {
        return repository.save(author);
    }

    public void deleteAuthor(Integer id) {
        repository.deleteById(id);
    }

    public List<AuthorEntity> getAuthors(String name) {
        return repository.findAllByName(name);
    }
}
