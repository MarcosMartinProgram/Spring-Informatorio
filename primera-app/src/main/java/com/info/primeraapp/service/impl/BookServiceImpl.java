package com.info.primeraapp.service.impl;

import com.info.primeraapp.domain.Book;
import com.info.primeraapp.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    Map<UUID, Book> bookMap;

    public BookServiceImpl() {
        bookMap = new HashMap<>();
        Book book = new Book();
        book.setUuid(UUID.randomUUID());
        book.setAuthor("Gabriel Garcia Marquez");
        book.setTitle("Cien años de soledad");

        Book book2 = new Book();
        book2.setUuid(UUID.randomUUID());
        book2.setAuthor("George Orwell");
        book2.setTitle("1984");

        Book book3 = new Book();
        book3.setUuid(UUID.randomUUID());
        book3.setAuthor("Antoine de Saint-Exupery");
        book3.setTitle("El Principito");

        bookMap.put(book.getUuid(),book);
        bookMap.put(book2.getUuid(),book2);
        bookMap.put(book3.getUuid(),book3);

    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book createBook(Book book) {
        book.setUuid(UUID.randomUUID());
        bookMap.put(book.getUuid(),book);

        return book;
    }
    @Override
    public List<Book> searchBooksByTitle(String title) {
        List<Book> searchResults = new ArrayList<>();

        for (Book book : bookMap.values()) {
            if (book.getTitle().contains(title)) {
                searchResults.add(book);
            }
        }

        return searchResults;
    }

    @Override
    public Optional<Book> updateBook(UUID uuidBook, Book bookUpdated) {
        //Buscamos libro
        Book book = bookMap.get(uuidBook);
        if(book != null){
            //Tratamos actualizacion
            updatingBook(book, bookUpdated);
            return Optional.of(book);

        }else {
            return Optional.empty();
        }
    }
    private void updatingBook(Book book, Book bookUpdated){
        if (bookUpdated.getTitle() != null){
            book.setTitle(bookUpdated.getTitle());
        }
        if (bookUpdated.getAuthor() != null){
            book.setAuthor(bookUpdated.getAuthor());
        }

    }

}
