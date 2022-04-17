package com.springbootparser.parser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModelDTO {
    private long id;
    private long orderId;
    private Double amount;
    private String currency;
    private String comment;
    private String fileName;
    private long line;
    private String result;

    @Override
    public String toString() {
        return "{id=" + id +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                ", fileName='" + fileName + '\'' +
                ", line=" + line +
                ", result='" + result + '\'' +
                '}';
    }
}
