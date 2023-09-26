package com.leo.demo.shopping.exception;

import com.leo.demo.shopping.models.base.ResponseCodeEnum;
import lombok.Data;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class BusinessException extends RuntimeException {

    /**
     * error code
     */
    private String code;

    private String detail;

    public BusinessException() {

    }

    public BusinessException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMessage());
        this.code = responseCodeEnum.getCode();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, String detail) {
        super(message);
        this.code = code;
        this.detail = detail;
    }

    protected BusinessException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}