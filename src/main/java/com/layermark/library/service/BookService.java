package com.layermark.library.service;

import com.layermark.library.exception.NotFoundException;
import com.layermark.library.repository.AuthorRepository;
import com.layermark.library.repository.BookRepository;
import com.layermark.library.repository.entity.AuthorEntity;
import com.layermark.library.repository.entity.BookEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository,
        AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookEntity getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book", id));
    }

    public BookEntity saveBook(Integer authorId, BookEntity book) {
        AuthorEntity author = new AuthorEntity();
        author.setId(authorId);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public BookEntity updateBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<BookEntity> getBooksByAuthorId(Integer authorId) {
        AuthorEntity author = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("author", authorId));
        return author.getBooks();
    }

    public List<BookEntity> getBooksByIsbnAndName(String isbn, String name) {
        return bookRepository.findByIsbnAndName(isbn, name);
    }
}
