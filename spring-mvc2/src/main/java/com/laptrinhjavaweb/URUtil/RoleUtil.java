package com.laptrinhjavaweb.URUtil;

public class RoleUtil {
	
	public String getRoleName(Long roleId) {
		if (roleId == 1) {
			return "Quản trị";
		} else {
			return "Người dùng";
		}
	}
	
	public Long getRoleId(String roleName) {
		if (roleName.equalsIgnoreCase("Quản trị")) {
			return  1L;
		} else {
			return 2L;
		}
	}

}
