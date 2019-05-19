package com.example.filecomparator.service;

import java.io.IOException;

public interface IFileComparisionService {
    String compareFiles(String[] files) throws IOException;
}
