package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.PublisherConverter;
import com.laptrinhjavaweb.dto.PublisherDTO;
import com.laptrinhjavaweb.entity.PublisherEntity;
import com.laptrinhjavaweb.repository.PublisherRepository;
import com.laptrinhjavaweb.service.IPublisherService;

@Service
public class PublisherService implements IPublisherService {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private PublisherConverter publisherConverter;
	
	@Override
	public Map<String, String> findAll() {
		Map<String, String> result = new HashMap<>();
		List<PublisherEntity> entities = publisherRepository.findAll();
		for (PublisherEntity item: entities) {
			result.put(item.getCode(), item.getName());
		}
		return result;
	}

	@Override
	public List<PublisherDTO> findAll(Pageable pageable) {
		List<PublisherDTO> models = new ArrayList<>();
		List<PublisherEntity> entities = publisherRepository.findAll(pageable).getContent();
		for (PublisherEntity item: entities) {
			PublisherDTO PublisherDTO = publisherConverter.toDto(item);
			models.add(PublisherDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) publisherRepository.count();
	}

	@Override
	public PublisherDTO findById(long id) {
		PublisherEntity entity = publisherRepository.findOne(id);
		return publisherConverter.toDto(entity);
	}

	@Override
	public PublisherDTO save(PublisherDTO dto) {
		PublisherEntity publisherEntity = new PublisherEntity();
		if (dto.getId() != null) {
			PublisherEntity oldPublisher = publisherRepository.findOne(dto.getId());
			publisherEntity = publisherConverter.toEntity(oldPublisher, dto);
		} else {
			publisherEntity = publisherConverter.toEntity(dto);
		}
		return publisherConverter.toDto(publisherRepository.save(publisherEntity));
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			publisherRepository.delete(id);
		}
	}
}
