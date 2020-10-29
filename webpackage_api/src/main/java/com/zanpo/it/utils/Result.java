package com.zanpo.it.utils;

import lombok.Data;

import java.util.UUID;

/**
 * 统一接口返回
 *
 * @author cg
 * @date 2020/10/28 22:47
 */
@Data
public class Result<T> {
    private String message;
    private T data;
    private String id;
    private boolean success;

    private Result(){}

    private Result(String message, T data, String id,boolean success) {
        this.message = message;
        this.data = data;
        this.id = id;
        this.success = success;
    }

    public static Result success(Object data){
        return new Result("调用成功",data, UUID.randomUUID().toString().replace("-",""),true);
    }

    public static Result failed(Object data){
        return new Result("调用失败",data, UUID.randomUUID().toString().replace("-",""),false);
    }
}
