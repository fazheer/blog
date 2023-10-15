package com.godfazheer.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageVO {
    private Long id;
    private String name;

    private String content;

    private Boolean isComment;


}
