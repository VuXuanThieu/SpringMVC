package com.laptrinhjavaweb.URUtil;

public class URModel {

	private Long userid;
	private Long roleid;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public URModel(Long userid, Long roleid) {
		super();
		this.userid = userid;
		this.roleid = roleid;
	}
	public URModel() {
		super();
	}
	
	

}
