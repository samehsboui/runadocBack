package com.app.runadoc.security.csrf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.app.runadoc.utils.StringsConstants;

public class SecurityCsrfFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

		if (csrf != null) {

			Cookie cookie = WebUtils.getCookie(request, StringsConstants.CSRF_COOKIE_NAME);
			String token = csrf.getToken();

			if (cookie == null || token != null && !token.equals(cookie.getValue())) {
				cookie = new Cookie(StringsConstants.CSRF_COOKIE_NAME, token);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}

		filterChain.doFilter(request, response);
	}
}
