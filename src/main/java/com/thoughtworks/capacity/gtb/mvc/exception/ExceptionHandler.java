package com.thoughtworks.capacity.gtb.mvc.exception;

import com.thoughtworks.capacity.gtb.mvc.dto.CommentError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({RegisterException.class,
            MethodArgumentNotValidException.class, LoginException.class, ConstraintViolationException.class})
    public ResponseEntity<CommentError> handleRegisterException(Exception ex) {
        CommentError commentError = new CommentError();
        commentError.setCode("400");
        if (ex instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                if (fieldError.getField().equals("username")) {
                    if (fieldError.getRejectedValue() == "") {
                        commentError.setMessage("用户名不能为空");
                    } else {
                        commentError.setMessage("用户名不合法");
                    }
                }
                if (fieldError.getField().equals("password")) {
                    if (fieldError.getRejectedValue() == "") {
                        commentError.setMessage("密码不能为空");
                    } else {
                        commentError.setMessage("密码不合法");
                    }
                }
                if (fieldError.getField().equals("email")) {
                    commentError.setMessage("邮箱地址不合法");
                }
            }

        }
        if (ex instanceof RegisterException) {
            commentError.setMessage("用户名已存在");
        }
        if (ex instanceof LoginException) {
            commentError.setMessage("用户名或密码错误");
        }
        if (ex instanceof ConstraintViolationException) {
            if(ex.getMessage().contains("username")){
                commentError.setMessage("用户名不合法");
            }else{
                commentError.setMessage("密码不合法");
            }
        }
        return ResponseEntity.status(400).body(commentError);
    }

}
