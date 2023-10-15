package com.godfazheer.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ArticleVO {
    private Long id;
    private String title;

    private String coverUrl;

    private String content;

    private Date createAt;

    private String categoryName;
    private List<String> tags;

    private Integer status;

    private Boolean isTop;

}
