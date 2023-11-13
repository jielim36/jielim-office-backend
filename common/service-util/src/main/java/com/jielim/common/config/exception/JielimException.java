package com.jielim.common.config.exception;

import com.jielim.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class JielimException extends RuntimeException{

    private Integer code;
    private String errorMessage;

    public JielimException(Integer code , String errorMessage){
        super(errorMessage);
        this.code = code;
        this.errorMessage =errorMessage;
    }

    public JielimException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.errorMessage = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "JielimException{" +
                "code=" + code +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
