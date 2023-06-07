package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @ResponseBody
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public Author getAuthor(@RequestParam Long authorId) {

        Optional<Author> entry = authorRepository.findById(authorId);
        Author author = entry.get();

        return author;
    }

    @ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.PUT)
    public Author createAuthor(@RequestParam String firstName, @RequestParam String lastName) {

        Author author1 = authorRepository.save(Author.builder().firstName(firstName).lastName(lastName).build());

        Optional<Author> entry = authorRepository.findById(author1.getId());
        Author author = entry.get();
        return author;
    }

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
}
