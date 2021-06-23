package com.example.lesson11task.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {
    private String date;

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private String factoreName;

    private String code;
}
