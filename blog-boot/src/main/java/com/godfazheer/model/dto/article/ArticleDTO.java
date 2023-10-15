package com.godfazheer.model.dto.article;

import com.godfazheer.model.dto.NeighborArticleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ArticleDTO {

    private Long id;

    private String title;

    private String coverUrl;

    private String content;

    private Date createAt;

    private Long views;

    private NeighborArticleDTO prevArticle;
    private NeighborArticleDTO nextArticle;
}
