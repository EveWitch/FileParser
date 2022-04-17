package com.springbootparser.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Component
public class JsonFileParser implements Parser {
    @Override
    public List<Model> parseFile (File file) {
        List<Model> models = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(file)) {
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
            ObjectMapper objectMapper = new ObjectMapper();
            for (Object object : jsonArray) {
                Model model = objectMapper.readValue(object.toString(), Model.class);
                models.add(model);
            }
        } catch (Exception e){
            System.out.println("File "+ file.getName() + " not found");
        }
          return models;
    }

}
