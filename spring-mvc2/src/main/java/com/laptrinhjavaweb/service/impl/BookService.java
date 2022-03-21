package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.BookConverter;
import com.laptrinhjavaweb.dto.BookDTO;
import com.laptrinhjavaweb.entity.AuthorEntity;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.PublisherEntity;
import com.laptrinhjavaweb.repository.AuthorRepository;
import com.laptrinhjavaweb.repository.BookRepository;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.PublisherRepository;
import com.laptrinhjavaweb.service.IBookService;

@Service
public class BookService implements IBookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private BookConverter bookConverter;

	@Override
	public List<BookDTO> findAll(Pageable pageable) {
		List<BookDTO> models = new ArrayList<>();
		List<BookEntity> entities = bookRepository.findAll(pageable).getContent();
		for (BookEntity item : entities) {
			BookDTO BookDTO = bookConverter.toDto(item);
			models.add(BookDTO);
		}
		return models;
	}
	
	@Override
	public List<BookDTO> findAllTitle(Pageable pageable, String keyword) {
		List<BookDTO> models = new ArrayList<>();
		List<BookEntity> entities = bookRepository.findAll(pageable).getContent();
		for (BookEntity item : entities) {
			BookDTO BookDTO = bookConverter.toDto(item);
			
			if (BookDTO.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
				models.add(BookDTO);
			}
		}
		return models;
	}
	
	@Override
	public int getTotalItemKeyword(String keyword) {
		int k = 0;
		List<BookEntity> entities = bookRepository.findAll();
		for (BookEntity item : entities) {
			BookDTO BookDTO = bookConverter.toDto(item);
			if (BookDTO.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
				k++;
			}
		}
		return k;
	}

	@Override
	public int getTotalItem() {
		return (int) bookRepository.count();
	}

	@Override
	public BookDTO findById(long id) {
		BookEntity entity = bookRepository.findOne(id);
		return bookConverter.toDto(entity);
	}

	@Override
	@Transactional
	public BookDTO save(BookDTO dto) {

		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		AuthorEntity author = authorRepository.findOneByCode(dto.getAuthorCode());
		PublisherEntity publisher = publisherRepository.findOneByCode(dto.getPublisherCode());

		BookEntity bookEntity = new BookEntity();
		if (dto.getId() != null) {
			BookEntity oldBook = bookRepository.findOne(dto.getId());

			oldBook.setCategory(category);
			oldBook.setAuthor(author);
			oldBook.setPublisher(publisher);

			bookEntity = bookConverter.toEntity(oldBook, dto);
		} else {
			bookEntity = bookConverter.toEntity(dto);

			bookEntity.setCategory(category);
			bookEntity.setAuthor(author);
			bookEntity.setPublisher(publisher);
		}
		return bookConverter.toDto(bookRepository.save(bookEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			bookRepository.delete(id);
		}
	}

	@Override
	public List<BookDTO> findAll() {
		List<BookDTO> models = new ArrayList<>();
		List<BookEntity> entities = bookRepository.findAll();
		for (BookEntity item : entities) {
			BookDTO BookDTO = bookConverter.toDto(item);
			models.add(BookDTO);
		}
		return models;
	}

	@Override
	public Map<Long, String> findAllString() {
		Map<Long, String> result = new HashMap<>();
		List<BookEntity> entities = bookRepository.findAll();
		for (BookEntity item : entities) {
			BookDTO bookDTO = bookConverter.toDto(item);
			if (bookDTO.getFk().equals("nfk")) {
				result.put(item.getId(), item.getTitle());
			}
			

		}
		return result;
	}

	


}
