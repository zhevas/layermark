package com.layermark.library.api;

import com.layermark.library.api.dto.AuthorDto;
import com.layermark.library.mapper.AuthorMapper;
import com.layermark.library.repository.entity.AuthorEntity;
import com.layermark.library.service.AuthorService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    public AuthorController(AuthorService service, AuthorMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Integer id) {
        AuthorEntity entity = service.getAuthorById(id);
        return mapper.entityToDto(entity);
    }

    @GetMapping
    public List<AuthorDto> getAuthors(@RequestParam(required = false) String name) {
        return service.getAuthors(name).stream().map(mapper::entityToDto).collect(Collectors.toList());
    }


    @PostMapping
    public ResponseEntity<Void> createAuthor(@RequestBody AuthorEntity author) {
        Integer id = service.saveAuthor(author).getId();
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/author/").path(id.toString()).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Integer id, @RequestBody AuthorDto dto) {
        AuthorEntity entity = mapper.dtoToEntity(dto);
        entity.setId(id);
        AuthorEntity newEntity = service.saveAuthor(entity);
        return mapper.entityToDto(newEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }


}
