package io.ljunggren.servlet.utils.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import io.ljunggren.servlet.utils.RequestInspector;

public class RequestInspectorFilter extends InspectorFilter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            RequestInspector requestInspector = new RequestInspector((HttpServletRequest) request);
            chain.doFilter(requestInspector, response);
            log(requestInspector);
            return;
        }
        chain.doFilter(request, response);
    }
    
    private void log(RequestInspector requestInspector) throws IOException {
        logger.log(getPathString(requestInspector));
        logger.log(getHeadersString(requestInspector));
        logger.log(getParametersString(requestInspector));
        logger.log(getBodyString(requestInspector));
    }
    
    private String getPathString(HttpServletRequest request) {
        return "Request URI: " + request.getRequestURI();
    }
    
    private String getHeadersString(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .map(headerName -> String.format("Request Header: [%s, %s]", headerName, request.getHeader(headerName)))
                .collect(Collectors.joining(System.lineSeparator()));
    }
    
    private String getParametersString(HttpServletRequest request) {
        return Collections.list(request.getParameterNames()).stream()
                .map(parameterName -> String.format("Request Parameter: [%s, %s]", parameterName, request.getParameter(parameterName)))
                .collect(Collectors.joining(System.lineSeparator()));
    }
    
    private String getBodyString(RequestInspector request) throws IOException {
        return request.getInputStream().read() == -1 ? "" : "Request Body: " + request.getBody();
    }
    
}
