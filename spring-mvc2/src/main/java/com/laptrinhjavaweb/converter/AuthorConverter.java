package com.laptrinhjavaweb.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.AuthorDTO;
import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.entity.AuthorEntity;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.repository.BookRepository;

@Component
public class AuthorConverter {
	
	@Autowired
	private BookRepository bookRepostory;
	
	@Autowired
	private BookConverter bookConverter;
	
	public AuthorDTO toDto(AuthorEntity entity) {
		AuthorDTO result = new AuthorDTO();
		result.setId(entity.getId());
		result.setCode(entity.getCode());
		result.setName(entity.getName());
		result.setAbout(entity.getAbout());
		
		//2911
		List<BookEntity> books = bookRepostory.findAll();
		for (BookEntity bookEntity : books) {
			BookDTO bookDTO = bookConverter.toDto(bookEntity);
			
			if (bookDTO.getAuthorCode().equals(entity.getCode())) {
				result.setFk("fk");
				break;
			}
		}
		return result;
	}
	
	public AuthorEntity toEntity(AuthorDTO dto) {
		AuthorEntity result = new AuthorEntity();
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		result.setAbout(dto.getAbout());
		return result;
	}
	
	//2911
	public AuthorEntity toEntity(AuthorEntity result, AuthorDTO dto) {
		result.setCode(dto.getCode());
		result.setName(dto.getName());
		result.setAbout(dto.getAbout());
		return result;
	}
}
