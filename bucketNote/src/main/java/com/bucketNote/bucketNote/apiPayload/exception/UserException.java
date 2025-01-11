package com.bucketNote.bucketNote.apiPayload.exception;

public class UserException extends RuntimeException{

    public UserException(String message) {
        super(message);
    }

    public static class UserNonExistsException extends UserException {
        public UserNonExistsException(String message) {
            super(message);
        }
    }

    public static class NicknameAlreadyExistsException extends UserException {
        public NicknameAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class InvalidTokenException extends UserException {
        public InvalidTokenException(String message) {
            super(message);
        }
    }

    public static class ExpiredTokenException extends UserException {
        public ExpiredTokenException(String message) {
            super(message);
        }
    }
}
