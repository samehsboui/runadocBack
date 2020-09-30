package com.app.runadoc.utils;

public class StringsConstants {

	// Generic Strings
	public static final String USERNAME = "username";
	public static final String USER = "user";
	public static final String MESSAGE = "message";

	// Exception Strings
	public static final String USER_NOT_EXIST = "User does not exist";
	public static final String EMAIL_TAKEN = "Email is already in use !";
	public static final String USERNAME_TAKEN = "Username is already taken !";

	public static final String ERROR_SENDING_MAIL = "Error while sending mail";

	public static final String CAN_NOT_SET_USER_AUTH = "Can NOT set user authentication -> Message: {}";
	public static final String UNAUTHORIZED_USER = "Unauthorized error. Message - {}";
	public static final String JWT_INVALID_SIGN = "Invalid JWT signature -> Message: {}";
	public static final String JWT_INVALID_TOKEN = "Invalid JWT token -> Message: {}";
	public static final String JWT_EXPIRED_TOKEN = "Expired JWT token -> Message: {}";
	public static final String JWT_UNSUPPORTED_TOKEN = "Unsupported JWT token -> Message: {}";
	public static final String JWT_EMPTY_CLAIMS = "JWT claims string is empty -> Message: {}";

	// Csrf Strings
	public static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
	public static final String[] CSRF_IGNORE = { "/api/auth/**", "/api/password/**", "/api/profile/**", "/api/user/**",
			"/api/rendezVous/**", "/api/etablissement/**" };

	// Mail Strings
	public static final String RESET_PASSWORD_MAIL = "reset-password-email";
	public static final String RESET_URL = "resetUrl";

	// Consumes MediaTypes
	public static final String APPLICATION_JSON = "application/json";

	// Urls Strings
	public static final String FRONT_BASE_URL = "http://localhost:4200";
	public static final String AUTH_URL = "/api/auth";
	public static final String USER_URL = "/api/user";
	public static final String PASSWORD_URL = "/api/password";
	public static final String PROFILE_URL = "/api/profile";
	public static final String TERRAIN_URL = "/api/etablissement";
	public static final String RESERVATION_URL = "/api/rendezVous";
}
