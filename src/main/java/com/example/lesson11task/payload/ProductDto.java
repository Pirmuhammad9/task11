package com.example.lesson11task.payload;

import com.example.lesson11task.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private Integer categoryId;
    private Integer attachmentId;
    private String code;
    private Integer measurementId;
    private boolean active;
}
