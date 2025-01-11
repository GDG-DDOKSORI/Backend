package com.bucketNote.bucketNote.apiPayload.exception;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentExceptionHandler {
    @ExceptionHandler(CommentException.CommentNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleBucketListExcption(CommentException.CommentNonExistsException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.COMMENT_NON_FOUND), HttpStatus.NOT_FOUND);
    }
}
