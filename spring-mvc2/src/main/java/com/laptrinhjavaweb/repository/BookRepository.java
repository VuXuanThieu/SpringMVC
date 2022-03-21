package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
	
}
