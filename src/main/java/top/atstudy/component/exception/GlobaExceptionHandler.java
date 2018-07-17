package top.atstudy.component.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sun.rmi.runtime.Log;
import top.atstudy.component.enums.base.IErrorEnum;
import top.atstudy.component.enums.http.IError400Enum;
import top.atstudy.component.enums.http.IError401Enum;
import top.atstudy.component.enums.http.IError403Enum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 18:17
 */
@ControllerAdvice
public class GlobaExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobaExceptionHandler.class);

    @ExceptionHandler({APIException.class, FrameworkException.class, Exception.class})
    public ResponseEntity<Map<String,String>> globaException(HttpServletRequest request, HttpServletResponse response, Exception exception){

        ResponseEntity responseEntity = null;
        if(exception instanceof APIException
            || exception instanceof FrameworkException){
            IErrorEnum errorEnum = ((FrameworkException)exception).getErrorEnum();
            Map<String, String> map = new HashMap<>();
            map.put("timestamp", new Date().toString());
            map.put("path", request.getRequestURI());
            map.put("code", errorEnum.getCode().toString());
            map.put("message", errorEnum.getMessage());
            if(errorEnum instanceof IError400Enum){
                responseEntity = new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
            }else if(errorEnum instanceof IError401Enum){
                responseEntity = new ResponseEntity<Map<String, String>>(map, HttpStatus.UNAUTHORIZED);
            }else if(errorEnum instanceof IError403Enum) {
                responseEntity = new ResponseEntity<Map<String, String>>(map, HttpStatus.FORBIDDEN);
            }
            logger.info("==>> {}", map.toString());
        }else {
            responseEntity = new ResponseEntity<Map<String, String>>(HttpStatus.INTERNAL_SERVER_ERROR);
            logger.info("==>> {}", exception.getMessage());
        }

        return responseEntity;
    }

}
