package com.kaizen.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RewardNotFoundException.class)
    public ResponseEntity<Object> handleRewardNotFoundException (RewardNotFoundException exception) {
        return new ResponseEntity<>("Such reward does not exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KaizenNotFoundException.class)
    public ResponseEntity<Object> handleKaizenNotFoundException (KaizenNotFoundException exception) {
        return new ResponseEntity<>("Such kaizen does not exist", HttpStatus.BAD_REQUEST);
    }
}