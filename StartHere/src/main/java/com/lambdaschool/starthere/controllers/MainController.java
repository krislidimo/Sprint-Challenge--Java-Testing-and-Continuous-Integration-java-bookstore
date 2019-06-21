package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Returns all Books.", response = Book.class, responseContainer = "List")
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> getAllBooks(@PageableDefault(page=0, size=5) Pageable pageable) {
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all Authors.", response = Author.class, responseContainer = "List")
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> getAllAuthors(@PageableDefault(page=0, size=5) Pageable pageable) {
        return new ResponseEntity<>(authorService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Ubdates a book by ID.", response = void.class)
    @PutMapping(value = "/data/books/{bookid}")
    public ResponseEntity<?> updateBook(
            @Valid @RequestBody
                    Book updatedBook,
            @PathVariable
                    long bookid) {
        bookService.update(updatedBook, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Assigns a book to an author.", response = void.class)
    @PostMapping(value = "/data/books/{bookid}/authors/{authid}",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> assignBookToAuthor(@PathVariable Long bookid,
                                                @PathVariable Long authid)
                                                throws URISyntaxException {
        bookService.assignBookToAuthor(bookid, authid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes a book.", response = void.class)
    @DeleteMapping("/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
