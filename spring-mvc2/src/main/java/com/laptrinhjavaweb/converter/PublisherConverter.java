package com.laptrinhjavaweb.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.dto.PublisherDTO;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.entity.PublisherEntity;
import com.laptrinhjavaweb.repository.BookRepository;

@Component
public class PublisherConverter {
	
	@Autowired
	private BookRepository bookRepostory;
	
	@Autowired
	private BookConverter bookConverter;
	
	public PublisherDTO toDto(PublisherEntity entity) {
		PublisherDTO result = new PublisherDTO();
		result.setId(entity.getId());
		result.setCode(entity.getCode());
		result.setName(entity.getName());
		result.setEmail(entity.getEmail());
		result.setAddress(entity.getAddress());
		
		//2911
		List<BookEntity> books = bookRepostory.findAll();
		for (BookEntity bookEntity : books) {
			BookDTO bookDTO = bookConverter.toDto(bookEntity);
			
			if (bookDTO.getPublisherCode().equals(entity.getCode())) {
				result.setFk("fk");
				break;
			}
		}
		
		return result;
	}
	
	public PublisherEntity toEntity(PublisherDTO dto) {
		PublisherEntity result = new PublisherEntity();
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		result.setEmail(dto.getEmail());
		result.setAddress(dto.getAddress());
		return result;
	}
	
	public PublisherEntity toEntity(PublisherEntity result,PublisherDTO dto) {
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		result.setEmail(dto.getEmail());
		result.setAddress(dto.getAddress());
		return result;
	}
}
