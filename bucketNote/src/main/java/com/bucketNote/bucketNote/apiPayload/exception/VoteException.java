package com.bucketNote.bucketNote.apiPayload.exception;

public class VoteException extends RuntimeException {
    public VoteException(String message) {
        super(message);
    }

    public static class VoteAlreadyExistException extends VoteException {
        public VoteAlreadyExistException(String message) {
            super(message);
        }
    }
}