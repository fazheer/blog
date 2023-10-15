package com.godfazheer.model.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class CategoriesVO {
    @Nullable
    private Long id;
    private String name;
}
