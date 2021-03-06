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
    public void init(FilterConfig filterConfig) {
        logger.info(" ===>> RequestFilter executor ... ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ReusableHttpServletRequestWrapper requestWrapper = new ReusableHttpServletRequestWrapper(request);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ReusableHttpServletResponseWrapper responseWrapper = new ReusableHttpServletResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);
    }

    @Override
    public void destroy() {

    }
}
