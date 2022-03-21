package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.AuthorDTO;
import com.laptrinhjavaweb.service.IAuthorService;

@RestController(value = "authorAPIOfAdmin")
public class AuthorAPI {
	
	@Autowired
	private IAuthorService authorService;
	
	@PostMapping("/api/author")
	public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
		
		
		
		return authorService.save(authorDTO);
	}
	
	@PutMapping("/api/author")
	public AuthorDTO updateAuthor(@RequestBody AuthorDTO updateAuthor) {
		return authorService.save(updateAuthor);
	}
	
	@DeleteMapping("/api/author")
	public void deleteAuthor(@RequestBody long[] ids) {
		authorService.delete(ids);
	}
}
