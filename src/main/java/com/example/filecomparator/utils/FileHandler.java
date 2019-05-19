package com.example.filecomparator.utils;

import java.io.*;

public class FileHandler {
    public static BufferedReader getBufferedReader(final String filePath, final String fileName)
            throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filePath + "/"+ fileName)));
    }

    public static void closeBufferedReader(final BufferedReader br) throws IOException {
        br.close();
    }
}
