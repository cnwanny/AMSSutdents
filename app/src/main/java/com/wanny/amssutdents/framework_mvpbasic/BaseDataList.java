package com.wanny.amssutdents.framework_mvpbasic;

/**
 * NAme : ${Name}
 * Author: wanny(**飞)
 * Time: 2018/2/26 17:10
 */

public class BaseDataList<T> {
    private boolean Status;
    private String Message;
    private int code;
    private String key;
    private T DataList;

    public T getDataList() {
        return DataList;
    }

    public void setDataList(T dataList) {
        DataList = dataList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
