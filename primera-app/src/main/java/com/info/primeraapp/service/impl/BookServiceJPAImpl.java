package com.info.primeraapp.service.impl;

import com.info.primeraapp.domain.Book;
import com.info.primeraapp.repository.book.BookRepository;
import com.info.primeraapp.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@AllArgsConstructor
public class BookServiceJPAImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll(); //Traer todos los libros
    }

    @Override
    public Book createBook(Book book) {
        book.setUuid(UUID.randomUUID());
        bookRepository.save(book); //Guardar en base de datos
        return book;
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return null;
    }



    @Override
    public Optional<Book> updateBook(UUID uuidBook, Book bookUpdated) {
        //Buscar libro por id
        Optional<Book> bookOptional = bookRepository.findById(uuidBook);
        if(bookOptional.isPresent()){
            updatingBook(bookOptional.get(),bookUpdated);
            //Save --> Si existe lo actualiza sino lo crea
            return Optional.of(bookRepository.save(bookOptional.get()));

        }else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteBook(UUID idBook) {
        Optional<Book> bookOptional = bookRepository.findById(idBook);
        if(bookOptional.isPresent()){
            bookRepository.delete(bookOptional.get());
            return Optional.of(true);
        } else {
            return Optional.of(false);
        }
    }

    private void  updatingBook(Book book, Book bookUpdated) {
        if (bookUpdated.getTitle() != null){
            book.setTitle(bookUpdated.getTitle());
        }

        if (bookUpdated.getAuthor() != null){
            book.setAuthor(bookUpdated.getAuthor());
        }
    }
    private boolean deleteBookByName(String title) {
        Optional<Book> bookOptional = bookRepository.findBookByTitleEqualsIgnoreCase(title);
        if (bookOptional.isPresent()){
            bookRepository.delete(bookOptional.get());
            return true;
        }

        return false;
    }
}
