package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.URUtil.IURDAO;
import com.laptrinhjavaweb.URUtil.RoleUtil;
import com.laptrinhjavaweb.URUtil.URDAO;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

@Component
public class UserConverter {
	
//	@Autowired
	private IURDAO iurdao = new URDAO();
	
//	@Autowired
	private RoleUtil roleUtil = new RoleUtil();

	public UserDTO toDto(UserEntity entity) {
		
		UserDTO result = new UserDTO();
		
		result.setId(entity.getId());
		
		result.setUserName(entity.getUserName());
		
		//0712
		//result.setPassword(entity.getPassword());
//		entity.setPassword("123456");	
		result.setPassword(entity.getPassword());
		
//		result.setPassword(encoder(entity.getPassword()));
		
		
		result.setFullName(entity.getFullName());
		result.setEmail(entity.getEmail());
		result.setPhone(entity.getPhone());
		
		//0212
		result.setRoleId(iurdao.findOne(entity.getId()).getRoleid());
		result.setRoleName(roleUtil.getRoleName(result.getRoleId()));
		
		return result;
	}
	
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity result = new UserEntity();
		
		result.setUserName(dto.getUserName());
		
		//0712
		dto.setPassword("$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG");
		result.setPassword(dto.getPassword());
		
		result.setFullName(dto.getFullName());
		result.setEmail(dto.getEmail());
		result.setPhone(dto.getPhone());
		result.setStatus(SystemConstant.ACTIVE_STATUS);
		
		return result;
	}
	
	public UserEntity toEntity(UserEntity result, UserDTO dto) {
		result.setUserName(dto.getUserName());
		
		//0712
		dto.setPassword("$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG");
		result.setPassword(dto.getPassword());
		result.setFullName(dto.getFullName());
		result.setEmail(dto.getEmail());
		result.setPhone(dto.getPhone());
		result.setStatus(SystemConstant.ACTIVE_STATUS);
		return result;
	}
}
