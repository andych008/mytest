package hello.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import hello.enums.ResponseError;
import hello.exception.MyException;
import hello.util.JacksonMapper;
import org.springframework.validation.Errors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {
    public interface View {}

    @JsonView(View.class)
    private boolean success;
    @JsonView(View.class)
    private String message;
    @JsonView(View.class)
    private String detail;
    @JsonView(View.class)
    private String stackTrace;
    @JsonView(View.class)
    private T data;
    @JsonView(View.class)
    private String errorCode;

    private ResponseResult() {
    }

    public static <T> ResponseResult<T> newInstance() {
        return new ResponseResult<>();
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setErrorInfo(ResponseError error) {
        this.errorCode = error.getCode();
        this.message = error.getMessage();
        this.detail = error.getDetail();
        this.stackTrace = error.getStackTrace();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDetail() {
        return detail;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    @Override
    public String toString() {
        return JacksonMapper.toJsonString(this);
    }

    public static <T> Builder<T> newBuilder() {
        return new Builder<>();
    }

    private static class Param {
        String message;//成功提示
        String errorMessage;//失败提示
    }

    public static class Builder<T> {
        private Param param = new Param();

        public Builder<T> successMsg(String message) {
            param.message = message;
            return this;
        }

        public Builder<T> failMsg(String errorMessage) {
            param.errorMessage = errorMessage;
            return this;
        }

        public Builder<T> failMsg(Errors errors, String errorMessage) {
            if (errors.hasErrors()) {
                throw new MyException(errors.getAllErrors().get(0).getDefaultMessage());
            } else {
                param.errorMessage = errorMessage;
            }
            return this;
        }

        public ResponseResult<T> build(T data) {
            if (data instanceof Boolean) {
                if ((Boolean) data) {
                    return genSuccessResult(param.message);
                } else {
                    throw new MyException(param.errorMessage == null ? "势行失败" : param.errorMessage);
                }
            } else {
                if (data == null) {
                    throw new MyException(param.errorMessage == null ? "未找到" : param.errorMessage);
                }
                return genResult(data, param.message);
            }
        }

        /**
         * 生成响应失败(带errorCode)的结果
         */
        public ResponseResult buildFailResult(ResponseError error) {
            ResponseResult result = new ResponseResult();
            result.setSuccess(false);
            result.setErrorInfo(error);
            return result;
        }

        /**
         * 生成响应成功(带正文)的结果
         *
         * @param data    结果正文
         * @param message 成功提示信息
         */
        private ResponseResult<T> genResult(T data, String message) {
            ResponseResult<T> result = new ResponseResult<>();
            result.setSuccess(true);
            result.setData(data);
            result.setMessage(message);
            return result;
        }


        private ResponseResult<T> genSuccessResult(String message) {
            ResponseResult<T> result = new ResponseResult<>();
            result.setSuccess(true);
            result.setMessage(message);
            return result;
        }
    }
}
