package top.atstudy.component.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-11
 * Time: 21:28
 */

@WebFilter
public class RequestFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info(" ===>> RequestFilter executor ... ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ReusableHttpServletRequestWrapper requestWrapper = new ReusableHttpServletRequestWrapper(request);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        ReusableHttpServletResponseWrapper responseWrapper = new ReusableHttpServletResponseWrapper(response);

        //解决跨域问题
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.addHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept,X-Cookie, token, withCredentials");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        if (requestWrapper.getMethod().equals("OPTIONS")) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().write("OPTIONS returns OK");
//            return;
//        }

        filterChain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
