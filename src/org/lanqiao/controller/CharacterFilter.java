package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter {

	FilterConfig config = null;
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String charset = config.getInitParameter("charset");
		request.setCharacterEncoding(charset);
		response.setContentType("text/html;charset="+charset);
		chain.doFilter(request, response);
		
		//1.设置服务器处理的字符编码
//		request.setCharacterEncoding("Utf-8");
//		response.setContentType("text/html;charset=UTF-8");

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
