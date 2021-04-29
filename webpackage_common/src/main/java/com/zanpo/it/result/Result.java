package com.zanpo.it.result;

import lombok.Data;

import java.util.UUID;

/**
 * 统一接口返回
 *
 * @author cg
 * @date 2020/10/28 22:47
 */
@Data
@SuppressWarnings("unchecked")
public class Result<T> {
    private String message;
    private T data;
    private String code;
    private boolean success;

    public Result() {
    }

    public Result(String code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public static <T> Result<T> success(T data){
        return new Result(ResultEnum.CALL_SUCCESS.getCode(),ResultEnum.CALL_SUCCESS.getMessage(), data,true);
    }

    public static <T> Result<T> failed(T data){
        return new Result(ResultEnum.PROGRAM_ERROR.getCode(), ResultEnum.PROGRAM_ERROR.getMessage(), data,false);
    }

    public static <T> Result<T> failed(){
        return new Result(ResultEnum.PROGRAM_ERROR.getCode(), ResultEnum.PROGRAM_ERROR.getMessage(), null,false);
    }

    public static <T> Result<T> failed(String code,String msg){
        return new Result(code,msg, null,false);
    }

    public static <T> Result<T> failed(ResultEnum resultEnum){
        return new Result(resultEnum.getCode(),resultEnum.getMessage(),null,false);
    }
}
