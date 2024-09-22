package io.ljunggren.servletUtils.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import io.ljunggren.servletUtils.ResponseInspector;

public class ResponseInspectorFilter extends InspectorFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            ResponseInspector responseInspector = new ResponseInspector((HttpServletResponse) response);
            chain.doFilter(request, responseInspector);
            log(responseInspector);
            return;
        }
        chain.doFilter(request, response);
    }
    
    private void log(ResponseInspector responseInspector) {
        logger.log(getResponseOutput(responseInspector));
    }
    
    private String getResponseOutput(ResponseInspector responseInspector) {
        return "Response: " + responseInspector.toString();
    }

}
