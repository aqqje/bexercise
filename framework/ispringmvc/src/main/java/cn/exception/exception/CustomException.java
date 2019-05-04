package cn.exception.exception;

/**
 * 自定义异常
 */
public class CustomException extends Exception {
    private String message;
    public CustomException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
