package top.atstudy.component.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-11
 * Time: 21:31
 */
public class ReusableHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest original;
    private byte[] reqBytes;
    private boolean firstTime = true;

    public ReusableHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.original = request;
    }

    public BufferedReader getReader() throws IOException {
        if (this.firstTime) {
            this.firstTime();
        }

        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(this.reqBytes));
        return new BufferedReader(isr);
    }

    public ServletInputStream getInputStream() throws IOException {
        if (this.firstTime) {
            this.firstTime();
        }

        ServletInputStream stream = new ServletInputStream() {
            private int readIndex = 0;

            public boolean isFinished() {
                return !ReusableHttpServletRequestWrapper.this.firstTime && ReusableHttpServletRequestWrapper.this.reqBytes.length == this.readIndex;
            }

            public boolean isReady() {
                boolean readyFlag = false;

                try {
                    readyFlag = ReusableHttpServletRequestWrapper.this.original.getInputStream().isReady();
                } catch (IOException var3) {
                    var3.printStackTrace();
                }

                return readyFlag;
            }

            public void setReadListener(ReadListener listener) {
            }

            public int read() throws IOException {
                byte b;
                if (ReusableHttpServletRequestWrapper.this.reqBytes.length > this.readIndex) {
                    b = ReusableHttpServletRequestWrapper.this.reqBytes[this.readIndex++];
                } else {
                    b = -1;
                }

                return b;
            }
        };
        return stream;
    }

    private synchronized void firstTime() throws IOException {
        if (this.firstTime) {
            this.firstTime = false;
            ServletInputStream sis = this.original.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024*10];
            int len = 0;
            while ((len = sis.read(b)) != -1){
                bos.write(b, 0, len);
            }

            this.reqBytes = bos.toByteArray();
            bos.close();
        }
    }
}
