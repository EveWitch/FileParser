package com.springbootparser.parser;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CountDownLatch;

@AllArgsConstructor
public class ConvertThread extends Thread{
   private ParserService parserService;
    private String fileName;
    CountDownLatch latch;
    @Override
    public void run() {
        parserService.parseFile(new File(fileName));
        parserService.convertModelToDTO(new File(fileName));
        latch.countDown();
    }
}
