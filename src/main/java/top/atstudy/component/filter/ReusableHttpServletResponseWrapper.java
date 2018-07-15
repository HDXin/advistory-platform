package top.atstudy.component.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 23:01
 */
public class ReusableHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private HttpServletResponse original;
    private byte[] respBytes;
    private boolean firstTime = true;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public ReusableHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        this.original = response;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if(this.firstTime){
            this.firstTime();
        }

        ByteArrayOutputStream bos  = new ByteArrayOutputStream();
        bos.write(this.respBytes);
        return new PrintWriter(new OutputStreamWriter(bos));
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        if(this.firstTime){
            this.firstTime();
        }

        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }

            @Override
            public void write(int b) throws IOException {

            }
        };
    }

    private synchronized void firstTime() throws IOException {
        if (this.firstTime) {
            this.firstTime = false;
            ServletOutputStream sos = this.original.getOutputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            this.respBytes = bos.toByteArray();
            sos.write(this.respBytes);
            sos.close();
            bos.close();
        }
    }
}
