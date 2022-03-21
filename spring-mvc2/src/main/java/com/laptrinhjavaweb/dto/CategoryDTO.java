package com.laptrinhjavaweb.dto;

public class CategoryDTO extends AbstractDTO<CategoryDTO> {
	
	private String name;
	private String code;
	
	private String fk;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}



}
