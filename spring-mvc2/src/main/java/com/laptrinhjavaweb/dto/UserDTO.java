package com.laptrinhjavaweb.dto;

public class UserDTO extends AbstractDTO<UserDTO> {

	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;

	private Long roleId;
	private String roleName;

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", password=" + password + ", fullName=" + fullName + ", email="
				+ email + ", phone=" + phone + ", roleId=" + roleId + ", roleName=" + roleName + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
