package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.PublisherDTO;

public interface IPublisherService {
	Map<String, String> findAll();
	
	//2911
	List<PublisherDTO> findAll(Pageable pageable);
	int getTotalItem();
	PublisherDTO findById(long id);
	PublisherDTO save(PublisherDTO dto);
	void delete(long[] ids);
}
