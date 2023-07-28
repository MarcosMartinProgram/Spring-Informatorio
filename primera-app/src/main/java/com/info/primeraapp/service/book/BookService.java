package com.info.primeraapp.service.book;

import com.info.primeraapp.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<Book> getAllBooks();

    Book createBook(Book book);

    List<Book> searchBooksByTitle(String title);

    Optional<Book> updateBook(UUID uuidBook, Book bookUpdated);

    Optional<Boolean> deleteBook(UUID idBook);
}


