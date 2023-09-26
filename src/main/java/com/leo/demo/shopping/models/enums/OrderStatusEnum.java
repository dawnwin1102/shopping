package com.leo.demo.shopping.models.enums;
public enum OrderStatusEnum {
    WaitPay("wait_pay","创建成功/待支付" ),
    PaySuccess("pay_success","支付成功/备餐中"),
    Cancel("cancel","取消(支付前)"),
    Refund("refund","取消(支付后)"),
    ;


    private String status;
    private String desc;


    OrderStatusEnum(String status, String desc){
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
