package com.cloud.spring.microservices.books.daos;

import com.cloud.spring.microservices.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BookDao extends JpaRepository<Book, Long>
{
    Set<Book> findByTitleContaining (String bookTitle);
}
