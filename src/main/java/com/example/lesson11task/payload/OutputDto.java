package com.example.lesson11task.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDto {

    private String date;

    private Integer warehouseId;

    private Integer currencyId;

    private String factureNumber;

    private String code;

    private Integer clientId;

}
