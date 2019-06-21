package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Book save(Book book) {
        Book newBook = new Book();
        newBook.setBooktitle(book.getBooktitle());
        newBook.setIsbn(book.getIsbn());
        newBook.setCopy(book.getCopy());

        return bookRepository.save(newBook);
    }

    @Transactional
    @Override
    public void assignBookToAuthor(long bookid, long authid) throws EntityNotFoundException {
        Book foundBook = bookRepository.findById(bookid).orElseThrow(() -> new EntityNotFoundException(Long.toString(bookid)));
        Author foundAuth = authorRepository.findById(authid).orElseThrow(() -> new EntityNotFoundException(Long.toString(authid)));

        foundBook.getAuthors().add(foundAuth);
        foundAuth.getBooks().add(foundBook);
    }

    @Transactional
    @Override
    public Book update(Book book, long id) throws EntityNotFoundException {
        Book currentBook = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (book.getBooktitle() != null)
        {
            currentBook.setBooktitle(book.getBooktitle());
        }
        if (book.getIsbn() != null)
        {
            currentBook.setIsbn(book.getIsbn());
        }
        if (book.getCopy() >= 0)
        {
            currentBook.setCopy(book.getCopy());
        }

        return currentBook;
    }

    @Transactional
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}
