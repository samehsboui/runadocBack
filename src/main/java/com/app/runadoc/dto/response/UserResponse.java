package com.app.runadoc.dto.response;

public class UserResponse {

	private Long id;

	private String fullName;

	public UserResponse() {
		super();
	}

	public UserResponse(Long id, String fullName) {
		super();
		this.id = id;
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
