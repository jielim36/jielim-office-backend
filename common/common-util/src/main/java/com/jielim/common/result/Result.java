package com.jielim.common.result;

import lombok.Data;

@Data//getter setter
public class Result<T>{

    private Integer code;
    private String message;
    private T data;

    private Result(){}

    protected  static <T> Result<T> build(T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        return result;
    }

    public static<T> Result<T> build(T body , Integer code , String message){
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static<T> Result<T> build(T body,ResultCodeEnum resultCodeEnum){
        Result<T> result = build(body);
        //status code
        result.setCode(resultCodeEnum.getCode());
        //json data message
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    //success
    public static<T> Result<T> ok(){
        return build(null,ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }

    //success
    public static<T> Result<T> fail(){
        return build(null,ResultCodeEnum.FAIL);
    }

    public static<T> Result<T> fail(T data){
        return build(data,ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}
