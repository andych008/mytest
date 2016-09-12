package hello.exception;

import hello.enums.ResponseError;
import hello.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.UnexpectedTypeException;

/**
 * RestController全局异常处理器
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 统一的rest接口异常处理器
     *
     * @param e 捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseResult globalExceptionHandler(Exception e) {

        logger.error("--------->接口调用异常!", e);
        ResponseError error = ResponseError.INTERNAL_SERVER_ERROR();
        if (e instanceof MyException) {
            error.setMessage(e.getMessage());
        } else {
            error.withDetail(e);
        }
        return ResponseResult.newBuilder().buildFailResult(error);
    }

    /**
     * bean校验未通过异常
     *
     * @see javax.validation.Valid
     * @see org.springframework.validation.Validator
     * @see org.springframework.validation.DataBinder
     */
    @ExceptionHandler({UnexpectedTypeException.class, HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseResult illegalParamsExceptionHandler(Exception e) {

        logger.error("--------->请求参数不合法!", e);
        return ResponseResult.newBuilder().buildFailResult(ResponseError.ILLEGAL_PARAMS().withDetail(e));
    }

}
