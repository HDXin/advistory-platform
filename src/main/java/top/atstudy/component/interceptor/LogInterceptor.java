package top.atstudy.component.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 17:19
 */
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    /**
     * 记录 request 数据
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        StringBuilder stringBuilder = new StringBuilder();
        if (request.getQueryString() != null) {
            requestURI = requestURI + "?" + request.getQueryString();
        }

        this.logger.info("--> <<{}>> {}", request.getMethod(), requestURI);
        if (request.getContentType() != null) {
            if (request.getContentType().contains("application/x-www-form-urlencoded")) {
                Map<String, String[]> parameterMap = request.getParameterMap();
                parameterMap.forEach((key, value) -> {
                    stringBuilder.append(key).append(" => ").append(value.length > 1 ? "\"" + value[0] + "\"(more)" : "\"" + value[0] + "\"").append("; ");
                });
            }

            if (request.getContentType().contains("multipart/form-data")) {
                stringBuilder.append(request.getContentLength() / 1000).append("KB");
            }

            if (request.getContentType().contains("application/json") || request.getContentType().contains("application/xml")) {
                String requestBody = getBody(request);
                stringBuilder.append(requestBody);
            }

            this.logger.info("--> <<Request-Content-Type>> : {}", request.getContentType());
            this.logger.info("--> {}", stringBuilder.toString());

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("---------------- <<Response Body>> ------------------------");
        this.logger.info("<-- <<Response-Http-Status>> : {}", response.getStatus());
        this.logger.info("<-- <<Response-Content-Type>> : {}", request.getContentType());
        this.logger.info("<-- {}", getResponseData());
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * 获取 body 数据
     * @param request
     * @return
     * @throws IOException
     */
    private String getBody(HttpServletRequest request) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getResponseData() {
        String result = MDC.get("X-RESPONSE-DATA");
        return result == null ? "" : result;
    }
}
