package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import com.laptrinhjavaweb.dto.BookDTO;

public interface IBookService {
	Map<Long, String> findAllString();
	List<BookDTO> findAll();
	List<BookDTO> findAll(Pageable pageable);
	List<BookDTO> findAllTitle(Pageable pageable, String keyword);
	int getTotalItem();
	int getTotalItemKeyword(String keyword);
	BookDTO findById(long id);
	BookDTO save(BookDTO dto);
	void delete(long[] ids);
}
