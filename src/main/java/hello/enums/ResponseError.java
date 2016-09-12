package hello.enums;


import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseError {
    public static ResponseError ILLEGAL_PARAMS() {
        return new ResponseError("ILLEGAL_PARAMS", "请求参数不合法!");

    }

    public static ResponseError INTERNAL_SERVER_ERROR() {
        return new ResponseError("INTERNAL_SERVER_ERROR", "接口内部异常!");

    }

    private ResponseError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    private String detail;

    private String stackTrace;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public ResponseError withDetail(Exception e) {
        this.detail = e.getMessage();
        // FIXME: 16/9/12 stackTrace 在product环境不显示
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        this.stackTrace = sw.toString();
        return this;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
