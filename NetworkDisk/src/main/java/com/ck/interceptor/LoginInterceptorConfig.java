package com.ck.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class LoginInterceptorConfig extends WebMvcConfigurationSupport {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**")// 拦截
				.excludePathPatterns("/js/**", "/css/**", "/img/**", "/fonts/**", "/user/login", "/user/logout",
						"/user/register", "/user/pwd", "/user/user/**", "/file/**", "/*");// 排除
		super.addInterceptors(registry);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {// 配置静态资源不被拦截
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/"); // 映射地址，资源路径
		super.addResourceHandlers(registry);
	}

}
