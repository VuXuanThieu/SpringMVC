package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.URUtil.IURDAO;
import com.laptrinhjavaweb.URUtil.RoleUtil;
import com.laptrinhjavaweb.URUtil.URDAO;
import com.laptrinhjavaweb.URUtil.URModel;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.BookEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	private IURDAO iurdao = new URDAO();
	private RoleUtil roleUtil = new RoleUtil();

	@Override
	public List<UserDTO> findAll(Pageable pageable) {
		List<UserDTO> models = new ArrayList<>();
		List<UserEntity> entities = userRepository.findAll(pageable).getContent();
		for (UserEntity item : entities) {
			UserDTO UserDTO = userConverter.toDto(item);
			models.add(UserDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) userRepository.count();
	}

	@Override
	public UserDTO findById(long id) {
		UserEntity entity = userRepository.findOne(id);
		return userConverter.toDto(entity);
	}

	@Override
	@Transactional
	public UserEntity save(UserDTO dto) {

		dto.setRoleId(roleUtil.getRoleId(dto.getRoleName()));

		UserEntity userEntity = new UserEntity();
		if (dto.getId() != null) {
			UserEntity oldUser = userRepository.findOne(dto.getId());
			userEntity = userConverter.toEntity(oldUser, dto);
		} else {
			userEntity = userConverter.toEntity(dto);
		}

		userRepository.save(userEntity);

		return userEntity;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (long id : ids) {
			userRepository.delete(id);
			iurdao.delete(id);
		}
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> models = new ArrayList<>();
		List<UserEntity> entities = userRepository.findAll();
		for (UserEntity item : entities) {
			UserDTO UserDTO = userConverter.toDto(item);
			models.add(UserDTO);
		}
		return models;
	}

	private URDAO urdao = new URDAO();

	@Override
	public Map<Long, String> findAllString() {
		Map<Long, String> result = new HashMap<>();
		List<UserEntity> entities = userRepository.findAll();
		
		//0412
		
		
		for (UserEntity item : entities) {
			if (urdao.findOne(item.getId()).getRoleid() == 2L)
			result.put(item.getId(), item.getFullName());
		}
		return result;
	}
}
