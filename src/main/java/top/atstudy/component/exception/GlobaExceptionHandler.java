package top.atstudy.component.exception;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import top.atstudy.component.base.vo.ErrorVo;
import top.atstudy.component.enums.base.IErrorEnum;
import top.atstudy.component.enums.http.IError400Enum;
import top.atstudy.component.enums.http.IError401Enum;
import top.atstudy.component.enums.http.IError403Enum;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 18:17
 */
@ControllerAdvice
public class GlobaExceptionHandler extends ResponseEntityExceptionHandler{
    private Logger logger = LoggerFactory.getLogger(GlobaExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler({APIException.class, FrameworkException.class, Throwable.class})
    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable throwable){

        //获取错误码
        HttpStatus status = getStatus(throwable);
        //获取错误信息
        ErrorVo errorVo = getErrorVo(request, throwable);
        errorVo.setPath(request.getRequestURI());
        logger.info("==>> {}", JSONObject.toJSONString(errorVo));

        return new ResponseEntity<>(errorVo, status);
    }

    /**
     * 获取状态码
     * @param throwable
     * @return
     */
    private HttpStatus getStatus(Throwable throwable) {
        if(throwable instanceof APIException
                || throwable instanceof FrameworkException){
            IErrorEnum errorEnum = ((FrameworkException)throwable).getErrorEnum();
            if(errorEnum instanceof IError400Enum){
                return HttpStatus.BAD_REQUEST;
            }else if(errorEnum instanceof IError401Enum){
                return HttpStatus.UNAUTHORIZED;
            }else if(errorEnum instanceof IError403Enum) {
                return HttpStatus.FORBIDDEN;
            }else{
                logger.info("===>> error: {}", throwable);
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        logger.info("===>> error: {}", throwable);
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private ErrorVo getErrorVo(HttpServletRequest request, Throwable throwable){

        ErrorVo errorVo = new ErrorVo();
        if(throwable instanceof APIException
                || throwable instanceof FrameworkException){
            IErrorEnum errorEnum = ((FrameworkException)throwable).getErrorEnum();
            errorVo.setCode((Integer)errorEnum.getCode());
            errorVo.setMessage(errorEnum.getMessage());
        }else{
            HttpStatus status = getStatus(request);
            errorVo.setCode(status.value());
            errorVo.setMessage(status.getReasonPhrase());
        }

        return errorVo;
    }


    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


}
