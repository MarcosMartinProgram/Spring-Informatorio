package com.info.primeraapp.repository.book;

import com.info.primeraapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    //Query Methods
    Optional<Book> findBookByTitleEqualsIgnoreCase(String title);
}
