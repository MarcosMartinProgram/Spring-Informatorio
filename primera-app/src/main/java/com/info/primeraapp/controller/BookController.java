package com.info.primeraapp.controller;


import com.info.primeraapp.domain.Book;
import com.info.primeraapp.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController  // Anotacion a nivel de clase
@RequestMapping("/api/v1/book") //Todos los endpoint comparten esta URI
@Slf4j
public class BookController {

    //IoC Inversion de control

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //GET --> obtener un recurso
    @GetMapping()
    public List<Book> getAllBooks(){
        log.info("Se está haciendo una consulta por un libro");
        return bookService.getAllBooks();
    }

    //POST --> Crear un recurso
    @PostMapping()
    public Book createBook(@RequestBody Book book){
        log.info("Creacion de un nuevo libro");
        return bookService.createBook(book);
    }

    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam("title") String title) {
        log.info("Se esta buscando un libro");
        return bookService.searchBooksByTitle(title);
    }

    //PUT --> Actualizar un libro
    @PutMapping("/{idBook}")
    public String updateBook(@PathVariable(value ="idBook") UUID idBook, @RequestBody Book bookUpdated){

        Optional<Book> book = bookService.updateBook(idBook, bookUpdated);

        if(book.isEmpty()){
            log.info("Libro no encontrado");
            return "Book not found";
        }else {
            log.info("Libro actualizado");
            return "/api/v1/book/"+book.get().getUuid();
        }

    }
    @DeleteMapping("/{idBook}")
    public String deleteBook(@PathVariable(value = "idBook") UUID idBook){
        Optional<Boolean> isDeleted = bookService.deleteBook(idBook);
        if (isDeleted.orElse(false)) {
            return "Book with Id " + idBook + "has been deleted.";
        }else {
            return "Book not found whit ID " + idBook + ". Deletion failed.";
        }
    }
}


