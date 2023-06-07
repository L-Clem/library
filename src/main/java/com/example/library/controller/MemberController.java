package com.example.library.controller;

import com.example.library.dto.MemberDTO;
import com.example.library.entity.Category;
import com.example.library.entity.Member;
import com.example.library.repository.CategoryRepository;
import com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @ResponseBody
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public Member getMember(@RequestParam Long memberId) {
        Optional<Member> entry = memberRepository.findById(memberId);
        Member member = entry.get();

        return member;
    }

    /*@ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.PUT)
    public MemberDTO createMember(@ResponseBody MemberDTO member){
        return member;
    }*/
}
/*
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
}*/
