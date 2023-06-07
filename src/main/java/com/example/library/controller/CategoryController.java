package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Category;
import com.example.library.repository.BookRepository;
import com.example.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @ResponseBody
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public Category getCategory(@RequestParam Long categoryId) {
        Iterable<Category> cat = categoryRepository.findAll();
        Optional<Category> entry = categoryRepository.findById(categoryId);
        Category category = entry.get();

        return category;
    }

    @ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.PUT)
    public Long createCategory(@RequestParam String name){
        Category category = categoryRepository.save(Category.builder().name(name).build());

        return category.getId();
    }

    @ResponseBody
    @RequestMapping(path="/delete", method = RequestMethod.DELETE)
    public boolean deleteCategory(@RequestParam Long categoryId) {
        Optional<Category> entry = categoryRepository.findById(categoryId);
        Category category = entry.get();

        if (!category.getBooks().isEmpty()) {
            return false;
        }

        categoryRepository.delete(category);
        return true;
    }
}
