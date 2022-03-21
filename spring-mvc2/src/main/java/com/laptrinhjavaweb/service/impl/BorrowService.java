package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.converter.BorrowConverter;
import com.laptrinhjavaweb.dto.BorrowDTO;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.entity.BorrowEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BookRepository;
import com.laptrinhjavaweb.repository.BorrowRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBorrowService;

@Service
public class BorrowService implements IBorrowService {
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BorrowConverter borrowConverter;
	
	@Override
	public List<BorrowDTO> findAll(Pageable pageable) {
		List<BorrowDTO> models = new ArrayList<>();
		List<BorrowEntity> entities = borrowRepository.findAll(pageable).getContent();
		for (BorrowEntity item: entities) {
			BorrowDTO BorrowDTO = borrowConverter.toDto(item);
			models.add(BorrowDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) borrowRepository.count();
	}

	@Override
	public BorrowDTO findById(long id) {
		BorrowEntity entity = borrowRepository.findOne(id);
		return borrowConverter.toDto(entity);
	}
	
	@Override
	@Transactional
	public BorrowDTO save(BorrowDTO dto) {
		
		UserEntity user = userRepository.findOne(dto.getUserId());
		BookEntity book = bookRepository.findOne(dto.getBookId());
		
		
		BorrowEntity borrowEntity = new BorrowEntity();
		if (dto.getId() != null) {
			BorrowEntity oldBorrow = borrowRepository.findOne(dto.getId());
			
			oldBorrow.setUser(user);
			oldBorrow.setBook(book);
			
			
			borrowEntity = borrowConverter.toEntity(oldBorrow, dto);
		} else {
			borrowEntity = borrowConverter.toEntity(dto);
			
			borrowEntity.setUser(user);
			borrowEntity.setBook(book);
		}
		return borrowConverter.toDto(borrowRepository.save(borrowEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id: ids) {
			borrowRepository.delete(id);
		}
	}

	
}
