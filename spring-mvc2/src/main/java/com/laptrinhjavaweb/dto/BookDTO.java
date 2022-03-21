package com.laptrinhjavaweb.dto;

public class BookDTO extends AbstractDTO<BookDTO> {
	
	private String title;
	private String thumbnail;
	private String shortDescription;
	private String content;
	
	private int year;
	
	private Long categoryId;
	private String categoryCode;
	
	private Long authorId;
	private String authorCode;
	
	private Long publisherId;
	private String publisherCode;
	
	private String fk;
	
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
	public Long getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherCode() {
		return publisherCode;
	}
	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "BookDTO [title=" + title + ", thumbnail=" + thumbnail + ", shortDescription=" + shortDescription
				+ ", content=" + content + ", year=" + year + ", categoryId=" + categoryId + ", categoryCode="
				+ categoryCode + ", authorId=" + authorId + ", authorCode=" + authorCode + ", publisherId="
				+ publisherId + ", publisherCode=" + publisherCode + "]";
	}
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	
}
