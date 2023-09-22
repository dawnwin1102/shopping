package com.leo.demo.shopping.models.dto;

import lombok.Data;

/**
 * @author leo
 * @date 2023/7/3
 */
@Data
public class ProfileAuthParams {
    private String shdrSession;
    private String shdrWebSession;
    private String shdrOpenId;
    private String shdrVisitorId;
    /**
     * 1:vistor model,0:auth model
     */
    private String shdrVisitorModelFlag;
    private String platform;
    private String activeSessionId;
}
