package com.jielim.common.config.exception;

import com.jielim.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //General Exception Handler (this method will execution when the exception is not match with any specific exception handler)
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        return Result.fail().message("Global Exception Handler is executed... | Error Message: " + e.getMessage());
    }

    //Specific Exception Handler
    @ExceptionHandler(ArithmeticException.class)
    public Result arithmeticException(Exception e){
        return Result.fail().message("Arithmetic Exception: " + e.getMessage());
    }

    @ExceptionHandler(JielimException.class)
    public Result customException(Exception e){
        return Result.fail().message("Custom Jielim Exception: " + e.getMessage());
    }

}
