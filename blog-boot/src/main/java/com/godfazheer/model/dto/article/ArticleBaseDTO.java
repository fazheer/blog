package com.godfazheer.model.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/*
* 这个DTO对象用来传输以下情况查询的文章
* 1.通过分类Id查询
* 2.通过标签id查询
* 3.文章的上一条文章和下一条文章*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBaseDTO {
    private Long id;
    private String title;
    private String coverUrl;
    private Date createAt;
}
