package com.yuan.common;

public class Result {

    public static final Result SUCCESS = new Result("success");

    private String code;

    private String msg;

    private Result(String code) {
        this.code = code;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
