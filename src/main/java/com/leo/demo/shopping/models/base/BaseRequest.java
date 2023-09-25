package com.leo.demo.shopping.models.base;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author leo
 * @date 2023/9/24
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaseRequest {
    @JsonIgnore
    private ShdrAuthInfo authInfo = new ShdrAuthInfo();
    @Data
    public class ShdrAuthInfo {
        @ApiModelProperty("Open ID：when pay channel = wechatpay public account or  jsapi, openId is required")
        private String sessionId;
        private String accessToken;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
