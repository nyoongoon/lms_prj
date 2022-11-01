package com.prj.lms.exception;

public class DuplicatedSignupException extends ProjectException{
    private static final String MESSAGE = "이미 가입된 회원입니다.";
    public DuplicatedSignupException(){
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
