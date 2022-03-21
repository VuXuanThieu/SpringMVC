package com.laptrinhjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.BorrowEntity;

public interface BorrowRepository extends JpaRepository<BorrowEntity, Long> {
	
}
