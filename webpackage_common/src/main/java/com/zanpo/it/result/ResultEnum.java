package com.zanpo.it.result;

/**
 * 结果错误信息枚举
 *
 * @author cg
 * @date 2021/4/28 17:42
 */
public enum ResultEnum {

    CALL_SUCCESS("200","调用成功"),

    PARAMS_MISS("400","参数异常"),

    PROGRAM_ERROR("500","程序异常");

    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误消息
     */
    private String message;

    ResultEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
