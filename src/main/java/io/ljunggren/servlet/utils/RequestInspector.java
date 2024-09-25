package io.ljunggren.servlet.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestInspector extends HttpServletRequestWrapper {
    
    private String body;

    public RequestInspector(HttpServletRequest request) {
        super(request);
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        body = MultipartRequestUtils.getBody(super.getInputStream());
        return new CustomServletInputSteam(super.getInputStream(), body.getBytes());
    }
    
    public String getBody() {
        return body;
    }
    
    private static class CustomServletInputSteam extends ServletInputStream {

        private ServletInputStream servletInputStream;
        private ByteArrayInputStream buffer;

        public CustomServletInputSteam(ServletInputStream servletInputStream, byte[] contents) {
            this.servletInputStream = servletInputStream;
            this.buffer = new ByteArrayInputStream(contents);
        }
        
        @Override
        public int read() throws IOException {
            return buffer.read();
        }

        @Override
        public boolean isFinished() {
            return servletInputStream.isFinished();
        }

        @Override
        public boolean isReady() {
            return servletInputStream.isReady();
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            servletInputStream.setReadListener(readListener);
        }
    }

}
