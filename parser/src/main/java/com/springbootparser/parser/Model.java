package com.springbootparser.parser;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class Model {
 @Override
 public String toString() {
  return  "{ orderId=" + orderId +
          ", amount=" + amount +
          ", currency='" + currency + '\'' +
          ", comment='" + comment + '\'' +
          '}';
 }

    @CsvBindByName
    private String orderId;
    @CsvBindByName
    private String amount;
    @CsvBindByName
    private String currency;
    @CsvBindByName
    private String comment;
}

