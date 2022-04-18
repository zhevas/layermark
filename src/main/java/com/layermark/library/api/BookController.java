package com.layermark.library.api;

import com.layermark.library.api.dto.BookDto;
import com.layermark.library.mapper.BookMapper;
import com.layermark.library.repository.entity.AuthorEntity;
import com.layermark.library.repository.entity.BookEntity;
import com.layermark.library.service.BookService;
import java.awt.print.Book;
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
@RequestMapping
public class BookController {

    private final BookService service;
    private final BookMapper mapper;

    public BookController(BookService service, BookMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable Integer id) {
        BookEntity entity = service.getBookById(id);
        return mapper.entityToDto(entity);
    }

    @GetMapping("/books")
    public List<BookDto> getBooks(
        @RequestParam(required = false) String isbn,
        @RequestParam(required = false) String name
    ) {
        return service.getBooksByIsbnAndName(isbn, name).stream().map(mapper::entityToDto).collect(Collectors.toList());
    }


    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<Void> createBook(@RequestBody BookEntity book, @PathVariable Integer authorId) {
        Integer id = service.saveBook(authorId, book).getId();
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/books/").path(id.toString())
            .build().toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/books/{id}")
    public BookDto updateBook(@PathVariable Integer id, @RequestBody BookDto dto) {
        BookEntity entity = mapper.dtoToEntity(dto);
        entity.setId(id);
        BookEntity newEntity = service.updateBook(entity);
        return mapper.entityToDto(newEntity);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/authors/{authorId}/books")
    public List<BookDto> getBooksByAuthor(@PathVariable Integer authorId) {
        return service.getBooksByAuthorId(authorId).stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}
