package com.bucketNote.bucketNote.apiPayload.exception;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.InvalidTokenException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidTokenException(UserException.InvalidTokenException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.TOKEN_INVALID), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserException.ExpiredTokenException.class)
    public ResponseEntity<ApiResponse<?>> handleExpiredTokenException(UserException.ExpiredTokenException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.TOKEN_EXPIRED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserException.UserNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNonExistsException(UserException.UserNonExistsException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.USER_NON_PRESENT), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserException.NicknameAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleNicknameAlreadyExistsException(UserException.NicknameAlreadyExistsException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.NICKNAME_DUPLICATE), HttpStatus.CONFLICT);
    }
}
