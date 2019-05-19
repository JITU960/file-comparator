package com.example.filecomparator.controller;

import com.example.filecomparator.errorcodes.ServiceExceptionCodes;
import com.example.filecomparator.exception.FileComparisionServiceException;
import com.example.filecomparator.service.IFileComparisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("file-comparision")
@Slf4j
public class FileComparisionController extends AbstractController {
    @Autowired
    IFileComparisionService fileComparisionService;

    @RequestMapping(value = "files", method = RequestMethod.POST)
    public String compareFiles(@Valid @RequestBody String[] files) throws IOException {
        if (files.length < 2) {
            throw new FileComparisionServiceException(
                    ServiceExceptionCodes.MIN_NO_OF_FILE.errCode(),
                    ServiceExceptionCodes.MIN_NO_OF_FILE.errMsg());
        }
        return fileComparisionService.compareFiles(files);
    }
}
