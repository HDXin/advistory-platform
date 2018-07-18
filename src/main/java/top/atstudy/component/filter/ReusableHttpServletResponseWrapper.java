package top.atstudy.component.filter;

import javax.servlet.ServletOutputStream;
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
        if(this.firstTime)
            this.firstTime();

        return super.getWriter();
    }


    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if(this.firstTime)
            this.firstTime();

        return super.getOutputStream();
    }

    /**
     * 将数据读入缓冲流
     */
    private void firstTime() throws IOException {

        if (this.firstTime) {
            this.firstTime = false;

        }
    }
}

