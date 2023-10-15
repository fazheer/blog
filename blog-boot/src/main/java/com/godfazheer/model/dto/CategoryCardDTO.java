package com.godfazheer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCardDTO {
    private Long id;
    private String name;
    private Long count;
}
