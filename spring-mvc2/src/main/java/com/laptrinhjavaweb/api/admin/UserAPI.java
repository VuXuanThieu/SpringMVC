package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.URUtil.URDAO;
import com.laptrinhjavaweb.URUtil.URModel;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.service.IUserService;

@RestController(value = "userAPIOfAdmin")
public class UserAPI {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserConverter userConverter;

	@PostMapping("/api/user")
	public UserDTO createUser(@RequestBody UserDTO userDTO) {
		
		//save dto đã được thực hiện
		UserEntity userEntity = userService.save(userDTO);
		URModel urModel = new URModel(userEntity.getId(), userDTO.getRoleId());
		URDAO urdao = new URDAO();
		urdao.save(urModel);
		
		return userConverter.toDto(userEntity);
	}

	@PutMapping("/api/user")
	public UserDTO updateUser(@RequestBody UserDTO updateUser) {
		UserEntity userEntity = userService.save(updateUser);
		
		URModel urModel = new URModel(updateUser.getId(), updateUser.getRoleId());
		URDAO urdao = new URDAO();
		
		//0312
		urdao.delete(urModel.getUserid());
		urdao.save(urModel);
		
		return userConverter.toDto(userEntity);
	}

	@DeleteMapping("/api/user")
	public void deleteUser(@RequestBody long[] ids) {
		userService.delete(ids);
	}
}
