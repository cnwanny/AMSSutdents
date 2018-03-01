package com.wanny.amssutdents.framework_mvpbasic;

/**
 * 文件名： BaseBean
 * 功能：
 * 作者： wanny
 * 时间： 9:46 2016/8/15
 */
public class BaseBean<T> {
    private boolean Status;
    private String Message;
    private String code;
    private T Data;
    private String key;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
