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
}
