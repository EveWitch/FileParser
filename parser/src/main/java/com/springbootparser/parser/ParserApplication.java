package com.springbootparser.parser;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ParserApplication implements CommandLineRunner {
	@Autowired
	private ParserService parserService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ParserApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}


	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			CountDownLatch latch = new CountDownLatch(args.length);
			for (String fileName : args) {
				Thread thread = new ConvertThread(parserService, fileName, latch);
				thread.start();
			}
			latch.await();

			for (ModelDTO modelDTO : parserService.getModelDTOList()) {
				System.out.println(modelDTO);
			}
	} else {
			System.out.println("No files to read found");
		}
	}

}
