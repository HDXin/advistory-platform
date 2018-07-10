package top.atstudy.component.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import top.atstudy.component.enums.base.IErrorEnum;
import top.atstudy.component.enums.http.IError400Enum;
import top.atstudy.component.enums.http.IError401Enum;
import top.atstudy.component.enums.http.IError403Enum;

import javax.servlet.http.HttpServletRequest;
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

    @ExceptionHandler({APIException.class,FrameworkException.class, Exception.class})
    public ResponseEntity<Map<String,String>> globaException(HttpServletRequest request, FrameworkException exception){

        IErrorEnum errorEnum = exception.getErrorEnum();
        Map<String, String> map = new HashMap<>();
        map.put("timestamp", new Date().toString());
        map.put("path", request.getRequestURI());

        ResponseEntity responseEntity = null;
        if(errorEnum instanceof IError400Enum){
            map.put("code", errorEnum.getCode().toString());
            map.put("message", errorEnum.getMessage());
            responseEntity = new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
        }else if(errorEnum instanceof IError401Enum){
            map.put("code", errorEnum.getCode().toString());
            map.put("message", errorEnum.getMessage());
            responseEntity = new ResponseEntity<Map<String, String>>(map, HttpStatus.UNAUTHORIZED);
        }else if(errorEnum instanceof IError403Enum) {
            map.put("code", errorEnum.getCode().toString());
            map.put("message", errorEnum.getMessage());
            responseEntity = new ResponseEntity<Map<String, String>>(map, HttpStatus.FORBIDDEN);
        }else{

            responseEntity = new ResponseEntity<Map<String, String>>(map, HttpStatus.FORBIDDEN);
        }


        return responseEntity;
    }

}
