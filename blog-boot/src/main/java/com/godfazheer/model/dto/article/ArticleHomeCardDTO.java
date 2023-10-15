package com.godfazheer.model.dto.article;

import lombok.Data;

import java.util.Date;

@Data

public class ArticleHomeCardDTO {
    private Long id;
    private String title;
    private String coverUrl;
    private String content;
    private Date createAt;
    private String categoryName;
    private Boolean isTop;
    private Integer commentNum;
}
