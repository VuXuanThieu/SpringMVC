package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.AuthorConverter;
import com.laptrinhjavaweb.dto.AuthorDTO;
import com.laptrinhjavaweb.entity.AuthorEntity;
import com.laptrinhjavaweb.repository.AuthorRepository;
import com.laptrinhjavaweb.service.IAuthorService;

@Service
public class AuthorService implements IAuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private AuthorConverter authorConverter;
	
	@Override
	public Map<String, String> findAll() {
		Map<String, String> result = new HashMap<>();
		List<AuthorEntity> entities = authorRepository.findAll();
		for (AuthorEntity item: entities) {
			result.put(item.getCode(), item.getName());
		}
		return result;
	}

	@Override
	public List<AuthorDTO> findAll(Pageable pageable) {
		List<AuthorDTO> models = new ArrayList<>();
		List<AuthorEntity> entities = authorRepository.findAll(pageable).getContent();
		for (AuthorEntity item: entities) {
			AuthorDTO AuthorDTO = authorConverter.toDto(item);
			models.add(AuthorDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) authorRepository.count();
	}

	@Override
	public AuthorDTO findById(long id) {
		AuthorEntity entity = authorRepository.findOne(id);
		return authorConverter.toDto(entity);
	}

	@Override
	public AuthorDTO save(AuthorDTO dto) {
		AuthorEntity authorEntity = new AuthorEntity();
		if (dto.getId() != null) {
			AuthorEntity oldAuthor = authorRepository.findOne(dto.getId());
			authorEntity = authorConverter.toEntity(oldAuthor, dto);
		} else {
			authorEntity = authorConverter.toEntity(dto);
		}
		return authorConverter.toDto(authorRepository.save(authorEntity));
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			authorRepository.delete(id);
		}
	}
}
