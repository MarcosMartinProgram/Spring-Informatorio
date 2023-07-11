package com.info.primeraapp.controller;


import com.info.primeraapp.domain.Book;
import com.info.primeraapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Anotacion a nivel de clase
public class BookController {

    //IoC Inversion de control

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //GET --> obtener un recurso
    @GetMapping("/api/v1/book")
    public List<Book> getAllBooks(){

        return bookService.getAllBooks();
    }

    //POST --> Crear un recurso
    @PostMapping("/api/v1/book")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @GetMapping("/api/v1/book/search")
    public List<Book> searchBooksByTitle(@RequestParam("title") String title) {
        return bookService.searchBooksByTitle(title);
    }
}


