package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> getAllBooks(@PageableDefault(page=0, size=5) Pageable pageable) {
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> getAllAuthors(@PageableDefault(page=0, size=5) Pageable pageable) {
        return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updatedBook,
            @PathVariable
                    long bookid) {
        bookService.update(updatedBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/data/books{bookid}/authors{authid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> assignBookToAuthor(@PathVariable Long bookid,
                                                @PathVariable Long authid) throws URISyntaxException {

        bookService.assignBookToAuthor(bookid, authid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
