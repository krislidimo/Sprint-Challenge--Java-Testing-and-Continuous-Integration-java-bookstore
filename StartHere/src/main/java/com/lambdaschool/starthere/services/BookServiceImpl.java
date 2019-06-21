package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
}
