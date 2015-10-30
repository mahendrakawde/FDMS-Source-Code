package fdms.reporting.crystal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class FakeHttpServletResponse implements HttpServletResponse {
    private HttpServletResponse mOriginalResponse;
    private OutputStream mOut;

    public FakeHttpServletResponse(HttpServletResponse originalResponse, OutputStream out)
      {
        mOriginalResponse = originalResponse;
        mOut = out;
        new FakeHttpServletResponse.TestPrintWriter(System.out);
      }

    public void addCookie(Cookie cookie)
      {}

    public void addDateHeader(String name, long date)
      {}

    public void addHeader(String name, String value)
      {}

    public void addIntHeader(String name, int value)
      {}

    public boolean containsHeader(String name)
      { System.err.println("containsHeader()");
        return true; }

    public String encodeRedirectUrl(String url)
      { return encodeRedirectURL(url); }

    public String encodeRedirectURL(String url)
      { return mOriginalResponse.encodeRedirectURL(url); }

    public String encodeUrl(String url)
      { return encodeURL(url); }

    public String encodeURL(String url)
      { return mOriginalResponse.encodeURL(url); }

    public void sendError(int sc)
      {}

    public void sendError(int sc, String msg)
      {}

    public void sendRedirect(String location)
      {}

    public void setDateHeader(String name, long date)
      {}

    public void setHeader(String name, String value)
      {}

    public void setIntHeader(String name, int value)
      {}

    public void setStatus(int sc)
      {}

    public void setStatus(int sc, String sm)
      {}

    public void flushBuffer()
      {}

    public int getBufferSize()
      { return mOriginalResponse.getBufferSize(); }

    public String getCharacterEncoding()
      { return mOriginalResponse.getCharacterEncoding(); }

    public String getContentType()
      { return null; }

    public java.util.Locale getLocale()
      { return mOriginalResponse.getLocale(); }

    public ServletOutputStream getOutputStream()
      { return new FakeServletOutputStream(mOut); }

    public PrintWriter getWriter()
      { return new PrintWriter(mOut); }

    public boolean isCommitted()
      { return false; }

    public void reset()
      {}

    public void resetBuffer()
      {}

    public void setBufferSize(int size)
      { mOriginalResponse.setBufferSize(size); }

    public void setCharacterEncoding(String charset)
      {}

    public void setContentLength(int len)
      {}

    public void setContentType(java.lang.String type)
      {}

    public void setLocale(java.util.Locale locale)
      {}

    private class FakeServletOutputStream extends ServletOutputStream
      {
        private OutputStream mOut;

        public FakeServletOutputStream(OutputStream out)
          {
            mOut = out;
          }

        public void write(int b) throws IOException
          {
            mOut.write(b);
          }
      }

    private class TestPrintWriter extends PrintWriter
      {
        public TestPrintWriter(OutputStream out)
          {
            super(out, true);
          }

        public boolean checkError()
          { return super.checkError(); }

        public void close()
          {
            System.err.println("PrintWriter close()");
            super.close();
          }

        public void flush()
          {
            System.err.println("PrintWriter flush()");
            super.flush();
          }

        public void print(boolean s)
          {
            System.err.println("PrintWriter print(boolean)");
            super.print(s);
          }

        public void print(char s)
          {
            System.err.println("PrintWriter print(char)");
            super.print(s);
          }

        public void print(char[] s)
          {
            System.err.println("PrintWriter print(char[])");
            super.print(s);
          }

        public void print(double d)
          {
            System.err.println("PrintWriter print(double)");
            super.print(d);
          }

        public void print(float f)
          {
            System.err.println("PrintWriter print(float)");
            super.print(f);
          }

        public void print(int s)
          {
            System.err.println("PrintWriter print(int)");
            super.print(s);
          }

        public void print(long s)
          {
            System.err.println("PrintWriter print(long)");
            super.print(s);
          }

        public void print(Object s)
          {
            System.err.println("PrintWriter print(Object)");
            super.print(s);
          }

        public void print(String s)
          {
            System.err.println("PrintWriter print(String)");
            super.print(s);
          }

        public void println(boolean s)
          {
            System.err.println("PrintWriter println(boolean)");
            super.println(s);
          }

        public void println(char s)
          {
            System.err.println("PrintWriter println(char)");
            super.println(s);
          }

        public void println(char[] s)
          {
            System.err.println("PrintWriter println(char[])");
            super.println(s);
          }

        public void println(double d)
          {
            System.err.println("PrintWriter println(double)");
            super.println(d);
          }

        public void println(float f)
          {
            System.err.println("PrintWriter println(float)");
            super.println(f);
          }

        public void println(int s)
          {
            System.err.println("PrintWriter println(int)");
            super.println(s);
          }

        public void println(long s)
          {
            System.err.println("PrintWriter println(long)");
            super.println(s);
          }

        public void println(Object s)
          {
            System.err.println("PrintWriter println(Object)");
            super.println(s);
          }

        public void println(String s)
          {
            System.err.println("PrintWriter println(String)");
            super.println(s);
          }

        protected void setError()
          { super.setError(); }

        public void write(char[] buf)
          {
            System.err.println("PrintWriter write(char[])");
            super.write(buf);
          }

        public void write(char[] buf, int off, int len)
          {
            System.err.println("PrintWriter write(char[], off, len)");
            super.write(buf, off, len);
          }

        public void write(int c)
          {
            System.err.println("PrintWriter write(int)");
            super.write(c);
          }

        public void write(String s)
          {
            System.err.println("PrintWriter write(String)");
            super.write(s);
          }

        public void write(String s, int off, int len)
          {
            System.err.println("PrintWriter write(String, off, len)");
            super.write(s, off, len);
          }
      }
  }
