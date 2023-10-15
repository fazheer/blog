package com.godfazheer.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MenuDTO {
    private String menuName;
    private String perm;
    private String path;
}
