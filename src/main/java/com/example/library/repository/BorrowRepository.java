package com.example.library.repository;

import com.example.library.entity.Borrow;
import org.springframework.data.repository.CrudRepository;

public interface BorrowRepository extends CrudRepository<Borrow, Long> {
}