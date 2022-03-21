package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.AuthorDTO;

public interface IAuthorService {
	Map<String, String> findAll();
	
	//2911
	List<AuthorDTO> findAll(Pageable pageable);
	int getTotalItem();
	AuthorDTO findById(long id);
	AuthorDTO save(AuthorDTO dto);
	void delete(long[] ids);
}
