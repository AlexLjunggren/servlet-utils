package io.ljunggren.servletUtils.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import io.ljunggren.servletUtils.FilterLogger;

public class InspectorFilter implements Filter {

    protected FilterLogger logger;
    
    @Override
    public void init(FilterConfig filterConfig) {
        String className = filterConfig.getInitParameter(FilterLogger.FILTER_LOGGER_CLASS);
        logger = instantiateLogger(className);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {}

    private FilterLogger instantiateLogger(String className) {
        try {
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
            return (FilterLogger) clazz.newInstance();
        } catch (Exception e) {
            return new FilterLogger();
        }
    }
    
}
