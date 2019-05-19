package com.example.filecomparator.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by siddhantzawar on 6/11/17.
 */
@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class ExceptionResponse implements Serializable {
        private String errorCode;
        private String message;
        private static final long serialVersionUID = 1L;
}
