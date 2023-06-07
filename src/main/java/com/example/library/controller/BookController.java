package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @ResponseBody
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public Book getBook(@RequestParam Long bookId) {
        Optional<Book> entry = bookRepository.findById(bookId);
        Book book = entry.get();

        return book;
    }

    @ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.PUT)
    public Long createBook(@RequestParam String title, @RequestParam short pages, @RequestParam Date parution, @RequestParam Collection<Category> categories, @RequestParam Collection<Author> authors){
        Book book = bookRepository.save(Book.builder().title(title).pages(pages).parution(parution).categories(categories).authors(authors).build());

        return book.getId();
    }

    @ResponseBody
    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public boolean deleteBook(@RequestParam Long bookId) {
        Optional<Book> entry = bookRepository.findById(bookId);
        Book book = entry.get();

        book.setDeleted(true);
        bookRepository.save(book);

        return true;
    }
}

/*


    @ResponseBody
    @RequestMapping(path="/delete", method = RequestMethod.DELETE)
    public boolean deleteAuthor(@RequestParam Long authorId) {
        Optional<Author> entry = authorRepository.findById(authorId);
        Author author = entry.get();

        if (!author.getBooks().isEmpty()) {
            return false;
        }

        authorRepository.delete(author);
        return true;
    }
}*/
