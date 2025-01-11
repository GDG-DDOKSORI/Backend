package com.bucketNote.bucketNote.apiPayload.exception;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VoteExceptionHandler {
    @ExceptionHandler(VoteException.VoteAlreadyExistException.class)
    public ResponseEntity<ApiResponse<?>> handleCommentException(VoteException.VoteAlreadyExistException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.VOTE_ALREADY_EXSISTS), HttpStatus.NOT_FOUND);
    }
}
