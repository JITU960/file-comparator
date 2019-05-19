package com.example.filecomparator.controller;

import com.example.filecomparator.errorcodes.DefaultExceptionCodes;
import com.example.filecomparator.exception.ExceptionResponse;
import com.example.filecomparator.exception.FileComparisionServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AbstractController {

    @ExceptionHandler({FileComparisionServiceException.class})
    @ResponseBody
    public ExceptionResponse handleFileComparisionServiceException(
            FileComparisionServiceException ex, HttpServletResponse httpResponse) {
        log.error("Service Exception occoured" + ex.getMessage(), ex);
        ExceptionResponse exception = new ExceptionResponse();
        exception.setErrorCode(ex.getErrCode());
        exception.setMessage(ex.getErrMsg());
        httpResponse.setStatus(403);
        return exception;
    }

    @ExceptionHandler({IOException.class})
    @ResponseBody
    public ExceptionResponse handleIOException(IOException ex, HttpServletRequest request,
                                               HttpServletResponse httpResponse) {
        log.error("IO Exception occurred :", ex);
        ExceptionResponse exception = new ExceptionResponse();
        exception.setMessage(DefaultExceptionCodes.INTERNAL_SERVER.errMsg());
        exception.setErrorCode(DefaultExceptionCodes.INTERNAL_SERVER.errCode());
        httpResponse.setStatus(500);
        return exception;
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ExceptionResponse handleRuntimeException(RuntimeException ex,
            HttpServletResponse httpResponse) {
        log.error("Runtime Exception occoured" + ex.getMessage(), ex);
        ExceptionResponse exception = new ExceptionResponse();
        exception.setErrorCode(DefaultExceptionCodes.INTERNAL_SERVER.errCode());
        exception.setMessage(DefaultExceptionCodes.INTERNAL_SERVER.errMsg());
        httpResponse.setStatus(500);
        return exception;
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ExceptionResponse handleException(Exception ex, HttpServletResponse httpResponse) {
        log.error("Exception occoured", ex);
        ExceptionResponse exception = new ExceptionResponse();
        exception.setErrorCode(DefaultExceptionCodes.INTERNAL_SERVER.errCode());
        exception.setMessage(DefaultExceptionCodes.INTERNAL_SERVER.errMsg());
        httpResponse.setStatus(500);
        return exception;
    }
}
