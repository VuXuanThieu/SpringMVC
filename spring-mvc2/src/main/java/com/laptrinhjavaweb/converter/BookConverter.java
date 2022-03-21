package com.laptrinhjavaweb.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.dto.BorrowDTO;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.entity.BorrowEntity;
import com.laptrinhjavaweb.repository.BorrowRepository;

@Component
public class BookConverter {
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private BorrowConverter borrowConverter;

	public BookDTO toDto(BookEntity entity) {
		BookDTO result = new BookDTO();
		result.setId(entity.getId());
		result.setTitle(entity.getTitle());
		result.setShortDescription(entity.getShortDescription());
		result.setContent(entity.getContent());
		result.setThumbnail(entity.getThumbnail());
		result.setYear(entity.getYear());
		
		result.setCategoryCode(entity.getCategory().getCode());
		
		result.setAuthorCode(entity.getAuthor().getCode());
		
		result.setPublisherCode(entity.getPublisher().getCode());
		
		//0412
		List<BorrowEntity> borrowEntities = borrowRepository.findAll();
		for (BorrowEntity borrowEntity : borrowEntities) {
			BorrowDTO borrowDTO = borrowConverter.toDto(borrowEntity);
			if (borrowDTO.getBookId() == entity.getId()) {
				result.setFk("fk");
				return result;
			}
		}
		
		result.setFk("nfk");
		return result;
	}
	
	public BookEntity toEntity(BookDTO dto) {
		BookEntity result = new BookEntity();
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		result.setThumbnail(dto.getThumbnail());
		result.setYear(dto.getYear());
		return result;
	}
	
	public BookEntity toEntity(BookEntity result, BookDTO dto) {
		result.setTitle(dto.getTitle());
		result.setShortDescription(dto.getShortDescription());
		result.setContent(dto.getContent());
		result.setThumbnail(dto.getThumbnail());
		result.setYear(dto.getYear());
		return result;
	}
}
