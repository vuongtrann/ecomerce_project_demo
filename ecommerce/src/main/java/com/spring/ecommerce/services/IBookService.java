package com.spring.ecommerce.services;

import com.spring.ecommerce.persistence.model.Book;

import java.util.List;

public interface IBookService {
    public List<Book> getAllBooks();
    public Book findBookByName(String name);
    public Book addBook(Book book);
    public Book updateBook(Long id,Book book);
    public String deleteBook(Long id);
}
