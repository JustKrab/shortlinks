package com.example.shortlinks.services.impl;

import com.example.shortlinks.services.GeneratorService;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    private final RandomStringGenerator randomStringGenerator;


    public GeneratorServiceImpl() {
        this.randomStringGenerator = new RandomStringGenerator
                .Builder().filteredBy(GeneratorServiceImpl::isLatinLetterOrDigit)
                .build();
    }

    public String generate(int length) {
        return randomStringGenerator.generate(length);
    }

    private static boolean isLatinLetterOrDigit(int codePoint) {
        return ('a' <= codePoint && codePoint <= 'z')
                || ('0' <= codePoint && codePoint <= '9');

    }

}
