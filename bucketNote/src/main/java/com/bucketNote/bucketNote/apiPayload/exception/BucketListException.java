package com.bucketNote.bucketNote.apiPayload.exception;

public class BucketListException {
    public static class BucketListNonExistsException extends UserException {
        public BucketListNonExistsException(String message) {
            super(message);
        }
    }
    public static class BucketListNoByUserException extends UserException {
        public BucketListNoByUserException(String message) {
            super(message);
        }
    }
}
