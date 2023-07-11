package com.info.primeraapp.service;

import com.info.primeraapp.domain.Book;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book createBook(Book book);

    List<Book> searchBooksByTitle(String title);
}


