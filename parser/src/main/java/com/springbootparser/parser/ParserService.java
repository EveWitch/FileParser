package com.springbootparser.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@NoArgsConstructor
@Getter
@Setter
public class ParserService {
    @Autowired
    JsonFileParser jsonFileParser;
    @Autowired
    CSVParser csvParser;
    @Autowired
    ConverterToDto converterToDto;

    private List<ModelDTO> modelDTOList = Collections.synchronizedList(new ArrayList<>());
    private List<Model> modelsFromCSV = new ArrayList<>();
    private List<Model> modelsFromJson = new ArrayList<>();

    public synchronized void convertModelToDTO(File file) {
        String extension = getFileExtension(file);
        switch (extension){
            case "csv" :
                modelDTOList = converterToDto.convertModel(modelsFromCSV, file, modelDTOList);
                break;
            case "json" :
                modelDTOList = converterToDto.convertModel(modelsFromJson, file, modelDTOList);
                break;
        }
    }


    public String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        } else return "";
    }


    public void parseFile(File file) {
        String extension = getFileExtension(file);
        switch (extension){
            case "csv" :
               modelsFromCSV = csvParser.parseFile(file);
               break;
            case "json" :
                modelsFromJson = jsonFileParser.parseFile(file);
                break;
        }
    }
}
