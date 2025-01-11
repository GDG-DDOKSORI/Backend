package com.bucketNote.bucketNote.apiPayload.exception;

public class CommentException extends RuntimeException {
    public CommentException(String message) {
        super(message);
    }
    public static class CommentNonExistsException extends CommentException {
        public CommentNonExistsException(String message) {
            super(message);
        }
    }
}
