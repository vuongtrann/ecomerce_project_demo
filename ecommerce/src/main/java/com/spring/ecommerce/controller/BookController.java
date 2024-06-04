package com.spring.ecommerce.controller;

import com.spring.ecommerce.persistence.model.Book;
import com.spring.ecommerce.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    public BookService bookService;

    @GetMapping("/getAll")
    public ResponseEntity <List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, books.size()>0?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getBookByID/{bookID}")
    public ResponseEntity<?> getBookByID(@PathVariable("bookID") Long bookID) {
        try {
            return new ResponseEntity<>(bookService.getBookById(bookID),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    /* add a new book*/
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/updateBook/{bookID}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookID, @RequestBody Book book) {
        try {
            bookService.updateBook(bookID, book);
            return new ResponseEntity<>("Updated book with ID : "+bookID, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //dang bug
    @DeleteMapping("deleteBook/{bookID}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookID) {
        try {
            bookService.deleteBook(bookID);
            return new ResponseEntity<>("Deleted Book with ID : "+bookID, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
