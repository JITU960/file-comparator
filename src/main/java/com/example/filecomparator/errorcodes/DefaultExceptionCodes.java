package com.example.filecomparator.errorcodes;

/**
 * Created by siddhantzawar on 6/11/17.
 */
public enum DefaultExceptionCodes {
    INTERNAL_SERVER("ER-0001", "Internal Server Exception");

    private String errCode;
    private String errMsg;

    private DefaultExceptionCodes(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String errCode() {
        return this.errCode;
    }

    public String errMsg() {
        return this.errMsg;
    }

}
