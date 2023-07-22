package com.info.primeraapp.controller;


import com.info.primeraapp.domain.Book;
import com.info.primeraapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    //PUT --> Actualizar un libro
    @PutMapping("/api/v1/book/{idBook}")
    public String updateBook(@PathVariable(value ="idBook") UUID idBook, @RequestBody Book bookUpdated){

        Optional<Book> book = bookService.updateBook(idBook, bookUpdated);

        if(book.isEmpty()){
            System.out.println("Book not found");
            return "Book not found";
        }else {
            System.out.println("Book updated");
            return "/api/v1/book/"+book.get().getUuid();
        }

    }
    @DeleteMapping("/api/v1/book/{idBook}")
    public String deleteBook(@PathVariable(value = "idBook") UUID idBook){
        Optional<Boolean> isDeleted = bookService.deleteBook(idBook);
        if (isDeleted.orElse(false)) {
            return "Book with Id " + idBook + "has been deleted.";
        }else {
            return "Book not found whit ID " + idBook + ". Deletion failed.";
        }
    }
}


