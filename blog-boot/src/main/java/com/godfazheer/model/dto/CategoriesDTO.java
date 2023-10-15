package com.godfazheer.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriesDTO {
    private Long id;

    private String name;
}
