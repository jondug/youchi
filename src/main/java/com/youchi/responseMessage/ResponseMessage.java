package com.youchi.responseMessage;

public class ResponseMessage {
    private int code;
    private Object data;
    private String errorMsg;

    public static ResponseMessage success(Object data){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(1);
        responseMessage.setData(data);
        return responseMessage;
    }

    public static ResponseMessage error(String msg){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(-1);
        responseMessage.setErrorMsg(msg);
        return responseMessage;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
