package com.thoughtworks.capacity.gtb.mvc.exception;

import com.thoughtworks.capacity.gtb.mvc.dto.CommentError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({RegisterException.class,
            MethodArgumentNotValidException.class})
    public ResponseEntity<CommentError> handleRegisterException(Exception ex) {
        CommentError commentError = new CommentError();
        if (ex instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                if (fieldError.getField().equals("username")) {
                    if (fieldError.getRejectedValue() == "") {
                        commentError.setError("用户名不能为空");
                    } else {
                        commentError.setError("用户名不合法");
                    }
                }
                if (fieldError.getField().equals("password")) {
                    if (fieldError.getRejectedValue() == "") {
                        commentError.setError("密码不能为空");
                    } else {
                        commentError.setError("密码不合法");
                    }
                }
                if (fieldError.getField().equals("email")) {
                    commentError.setError("邮箱地址不合法");
                }
            }

        }
        if(ex instanceof RegisterException) {
            commentError.setError("用户名已存在");
        }

            return ResponseEntity.status(400).body(commentError);
    }

}
