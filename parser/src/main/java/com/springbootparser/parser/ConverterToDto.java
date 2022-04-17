package com.springbootparser.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Component
public class ConverterToDto {

    public List<ModelDTO> convertModel(List<Model> models, File file, List<ModelDTO> modelDTOs) {
        for (int i = 0; i < models.size(); i++) {
            Model model = models.get(i);
            ModelDTO modelDTO = new ModelDTO();
            int numberOfLines = modelDTOs.size();
            modelDTO.setId(numberOfLines+1);
            modelDTO.setOrderId(Long.parseLong(model.getOrderId()));
            modelDTO.setFileName(file.getName());
            modelDTO.setLine(i+1);
            modelDTO.setComment(model.getComment());
            modelDTO.setCurrency(model.getCurrency());
            if (isAmountNumeric(model.getAmount())) {
                modelDTO.setAmount(Double.parseDouble(model.getAmount()));
                modelDTO.setResult("OK");
            } else {
                modelDTO.setAmount(null);
                modelDTO.setResult("Amount is not numeric");
            }
            modelDTOs.add(modelDTO);
        }
        return modelDTOs;
    }

    private static boolean isAmountNumeric (String amount) {
        try {
            Double.parseDouble(amount);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
