package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.BorrowDTO;

public interface IBorrowService {
	List<BorrowDTO> findAll(Pageable pageable);
	int getTotalItem();
	BorrowDTO findById(long id);
	BorrowDTO save(BorrowDTO dto);
	void delete(long[] ids);
}
