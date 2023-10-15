package com.godfazheer.model.vo;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagVO {
    @Nullable
    private Long id;
    private String name;
}
