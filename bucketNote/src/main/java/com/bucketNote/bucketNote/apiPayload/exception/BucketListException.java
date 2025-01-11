package com.bucketNote.bucketNote.apiPayload.exception;

public class BucketListException extends RuntimeException{
    public BucketListException(String message) {
        super(message);
    }
    public static class BucketListNonExistsException extends BucketListException {
        public BucketListNonExistsException(String message) {
            super(message);
        }
    }
    public static class BucketListNoByUserException extends BucketListException {
        public BucketListNoByUserException(String message) {
            super(message);
        }
    }
}
