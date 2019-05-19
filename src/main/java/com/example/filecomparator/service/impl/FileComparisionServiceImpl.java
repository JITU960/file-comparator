package com.example.filecomparator.service.impl;

import com.example.filecomparator.errorcodes.ServiceExceptionCodes;
import com.example.filecomparator.exception.FileComparisionServiceException;
import com.example.filecomparator.service.IFileComparisionService;
import com.example.filecomparator.utils.FileHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@PropertySource("classpath:app.properties")
@Slf4j
public class FileComparisionServiceImpl implements IFileComparisionService {

    @Value("${base.directory}")
    String baseDirectory;

    public String compareFiles(String[] files) throws IOException {
        System.out.println("in compare files---------------------------------");
        Set<String> commonWords = null;
        commonWords = processFile(files[0], commonWords, false);
        for (int i = 1; i < files.length; i++) {
            commonWords = processFile(files[i], commonWords, true);
        }
        return prepareResponse(commonWords);
    }

    private Set<String> processFile(final String fileName, Set<String> wordsCommonInProcessedFiles,
            boolean isCompareWords) throws IOException, FileComparisionServiceException {
        Set<String> commonWords = new HashSet<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = FileHandler.getBufferedReader(baseDirectory, fileName);
        } catch (FileComparisionServiceException fcse) {
            throw new FileComparisionServiceException(
                    ServiceExceptionCodes.WRONG_FILE_NAME.errCode(),
                    ServiceExceptionCodes.WRONG_FILE_NAME.errMsg());
        }

        String line;
        try {
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    String[] words = getWordsFromLine(line);
                    for (String word : words) {
                        String eqWord = getLowerCaseWithoutSpeCharWord(word);
                        if (isCompareWords) {
                            if (wordsCommonInProcessedFiles.contains(eqWord)) {
                                commonWords.add(eqWord);
                            }
                        } else {
                            commonWords.add(eqWord);
                        }
                    }
                }
            } while (line != null);
        } finally {
            try {
                FileHandler.closeBufferedReader(bufferedReader);
            } catch (Exception e) {
                // log message exception while closing buffered reader
            }
        }
        System.out.println(commonWords);
        return commonWords;
    }

    private String[] getWordsFromLine(String line) {
        if (line != null) {
            String[] words = line.trim().split(" ");
            String[] equivalentWords = new String[words.length];
            for (int i = 0; i < words.length; i++) {
                equivalentWords[i] = getLowerCaseWithoutSpeCharWord(words[i]);
            }
            return equivalentWords;
        }
        return null;
    }

    private String getLowerCaseWithoutSpeCharWord(String str) {
        if (str == null) {
            return str;
        }
        int len = str.length();
        if (len == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < len) {
            if (Character.isDigit(str.charAt(i)) || Character.isAlphabetic(str.charAt(i))) {
                sb.append(Character.toLowerCase(str.charAt(i)));
            }
            i++;
        }
        return sb.toString();
    }

    private String prepareResponse(Set<String> words) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = words.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append(iterator.next().toString() + " ");
        }
        return stringBuilder.toString().trim();
    }
}
