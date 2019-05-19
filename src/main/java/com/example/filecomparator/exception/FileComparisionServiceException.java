package com.example.filecomparator.exception;

public class FileComparisionServiceException extends GenericException {
    private static final long serialVersionUID = -1663092887536384506L;

    public FileComparisionServiceException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
