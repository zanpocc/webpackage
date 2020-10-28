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
public class ResultSet<T> {
    private String status;
    private T data;
    private String id;

    private ResultSet(){}
    
    private ResultSet(String status, T data, String id) {
        this.status = status;
        this.data = data;
        this.id = id;
    }

    public static ResultSet success(Object data){
        return new ResultSet("success",data, UUID.randomUUID().toString().replace("-",""));
    }

    public static ResultSet failed(Object data){
        return new ResultSet("failed",data, UUID.randomUUID().toString().replace("-",""));
    }
}
