package com.godfazheer.model.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleBaseYearDTO {
    private Long id;
    private String title;
    private String coverUrl;
    private Date createAt;
    private String year;
}
