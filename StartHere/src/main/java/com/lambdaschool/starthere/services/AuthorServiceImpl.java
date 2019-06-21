package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
}
