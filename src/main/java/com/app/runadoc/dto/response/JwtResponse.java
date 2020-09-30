package com.app.runadoc.dto.response;

public class JwtResponse {

	private String token;
	private String name;
	private String email;
	private String image;

	public JwtResponse() {
		super();
	}

	public JwtResponse(String token, String email, String name, String image) {
		this.token = token;
		this.name = name;
		this.email = email;
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}