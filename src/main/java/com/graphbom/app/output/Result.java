package com.graphbom.app.output;

import java.io.Serializable;

public class Result<String,T> implements Serializable {

    public Result(String message, T data){
        this.message = message;
        this.data = data;
    }
    private String message;
    private T data;

    public Result() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
