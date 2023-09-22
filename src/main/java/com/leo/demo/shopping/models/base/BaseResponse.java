package com.leo.demo.shopping.models.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author leo
 * @date 2023/1/17
 */
@Data
public class BaseResponse<T> {
    private String code;
    private String message;
    private T result;
    private long time;
    public BaseResponse() {
        this(ResponseCode.Code_0000);
    }

    public BaseResponse(ResponseCode codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
        this.time = System.currentTimeMillis();
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
