package com.springbootparser.parser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter@Setter
@Component
public class CSVParser implements Parser {

    @Override
    public List<Model> parseFile (File file) {
        List <Model> models = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file)) {
            CsvToBean<Model> csvToBean = new CsvToBeanBuilder(fileReader)
                    .withType(Model.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            models = csvToBean.parse();
        } catch (FileNotFoundException e) {
            System.out.println("File "+ file.getName()+" not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return models;
    }
}
