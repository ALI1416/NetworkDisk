package com.ck.interceptor;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

//@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		ErrorPage error400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error.html");
		ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.html");
		registry.addErrorPages(error400);
		registry.addErrorPages(error500);
	}
}
