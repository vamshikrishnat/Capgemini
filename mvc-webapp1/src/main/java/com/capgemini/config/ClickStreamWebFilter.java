package com.capgemini.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns = "/*")
public class ClickStreamWebFilter implements Filter {

	  private static final Logger logger = LoggerFactory.getLogger(ClickStreamWebFilter.class);

	  @Override
	    public void init(FilterConfig filterConfig) throws ServletException {

	    }

	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        logger.info("filter:"+ ((HttpServletRequest)servletRequest).getRequestURL());
	        filterChain.doFilter(servletRequest, servletResponse);
	        System.out.println("filte  call");
	    }

	    @Override
	    public void destroy() {

	    }

}
