package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.PublisherDTO;
import com.laptrinhjavaweb.service.IPublisherService;

@RestController(value = "publisherAPIOfAdmin")
public class PublisherAPI {
	
	@Autowired
	private IPublisherService publisherService;
	
	@PostMapping("/api/publisher")
	public PublisherDTO createPublisher(@RequestBody PublisherDTO publisherDTO) {
		return publisherService.save(publisherDTO);
	}
	
	@PutMapping("/api/publisher")
	public PublisherDTO updatePublisher(@RequestBody PublisherDTO updatePublisher) {
		return publisherService.save(updatePublisher);
	}
	
	@DeleteMapping("/api/publisher")
	public void deletePublisher(@RequestBody long[] ids) {
		publisherService.delete(ids);
	}
}
