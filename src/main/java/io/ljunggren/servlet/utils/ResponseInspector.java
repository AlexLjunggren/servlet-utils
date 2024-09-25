package io.ljunggren.servlet.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseInspector extends HttpServletResponseWrapper {

    private final CopyPrintWriter writer;

    public ResponseInspector(HttpServletResponse response) throws IOException {
        super(response);
        writer = new CopyPrintWriter(response.getWriter());
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public String toString() {
        return writer.getCopy();
    }

    public class CopyPrintWriter extends PrintWriter {

        private StringBuilder copy = new StringBuilder();

        public CopyPrintWriter(Writer writer) {
            super(writer);
        }

        @Override
        public void write(int c) {
            copy.append((char) c);
            super.write(c);
        }

        @Override
        public void write(char[] chars, int offset, int length) {
            copy.append(chars, offset, length);
            super.write(chars, offset, length);
        }

        @Override
        public void write(String string, int offset, int length) {
            copy.append(string, offset, length);
            super.write(string, offset, length);
        }

        public String getCopy() {
            return copy.toString();
        }

    }
    
}
