package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.BorrowDTO;
import com.laptrinhjavaweb.service.IBorrowService;

@RestController(value = "borrowAPIOfAdmin")
public class BorrowAPI {
	
	@Autowired
	private IBorrowService borrowService;
	
	@PostMapping("/api/borrow")
	public BorrowDTO createBorrow(@RequestBody BorrowDTO borrowDTO) {
		return borrowService.save(borrowDTO);
	}
	
	@PutMapping("/api/borrow")
	public BorrowDTO updateBorrow(@RequestBody BorrowDTO updateBorrow) {
		return borrowService.save(updateBorrow);
	}
	
	@DeleteMapping("/api/borrow")
	public void deleteBorrow(@RequestBody long[] ids) {
		borrowService.delete(ids);
	}
}
