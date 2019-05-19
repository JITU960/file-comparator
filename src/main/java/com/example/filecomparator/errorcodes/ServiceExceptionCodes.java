package com.example.filecomparator.errorcodes;

public enum ServiceExceptionCodes {
    MIN_NO_OF_FILE("ER-S001","At least two file must be there for comparision"),
    WRONG_FILE_NAME("ER-S002", "One of the file passed as parameter does not exist in system");

    private String errCode;
    private String errMsg;

    ServiceExceptionCodes(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String errCode() {
        return this.errCode;
    }

    public String errMsg() {
        return this.errMsg;
    }

    public static ServiceExceptionCodes forErrCode(String errCode) {
        ServiceExceptionCodes[] allCodes = ServiceExceptionCodes.values();
        for(ServiceExceptionCodes code : allCodes) {
            if(code.errCode.equals(errCode)) {
                return code;
            }
        }
        return null;
    }
}
