package com.laptrinhjavaweb.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.repository.BookRepository;

@Component
public class CategoryConverter {
	
	@Autowired
	private BookRepository bookRepostory;
	
	@Autowired
	private BookConverter bookConverter;
	
	public CategoryDTO toDto(CategoryEntity entity) {
		CategoryDTO result = new CategoryDTO();
		result.setId(entity.getId());
		result.setCode(entity.getCode());
		result.setName(entity.getName());
		
		//2911
		List<BookEntity> books = bookRepostory.findAll();
		for (BookEntity bookEntity : books) {
			BookDTO bookDTO = bookConverter.toDto(bookEntity);
			
			if (bookDTO.getCategoryCode().equals(entity.getCode())) {
				result.setFk("fk");
				break;
			}
		}
		return result;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity result = new CategoryEntity();
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		return result;
	}
	
	//2911
	public CategoryEntity toEntity(CategoryEntity result, CategoryDTO dto) {
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		return result;
	}
	
	
}
