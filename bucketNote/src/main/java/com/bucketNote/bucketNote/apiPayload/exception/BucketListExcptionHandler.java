package com.bucketNote.bucketNote.apiPayload.exception;

import com.bucketNote.bucketNote.apiPayload.ApiResponse;
import com.bucketNote.bucketNote.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bucketNote.bucketNote.apiPayload.exception.BucketListException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BucketListExcptionHandler {
    @ExceptionHandler(BucketListException.BucketListNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleBucketListExcption(BucketListException.BucketListNonExistsException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.BUCKETLIST_NON_PRESENT), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BucketListException.BucketListNoByUserException.class)
    public ResponseEntity<ApiResponse<?>> handleBucketListExcption(BucketListException.BucketListNoByUserException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.BUCKETLIST_NO_BY_USER), HttpStatus.NOT_FOUND);
    }
}
