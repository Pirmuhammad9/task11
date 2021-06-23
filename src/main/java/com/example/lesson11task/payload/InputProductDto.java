package com.example.lesson11task.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {

    private Integer productId;

    private Integer inputId;

    private String amount;

    private double price;

    private Date expireDate;

}
