package ru.netology.l20authservice.service;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException(String msg) {
        super(msg);
    }
}
