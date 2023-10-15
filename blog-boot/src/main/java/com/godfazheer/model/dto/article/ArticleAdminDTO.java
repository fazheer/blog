package com.godfazheer.model.dto.article;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleAdminDTO {
    private Long id;
    private String coverUrl;
    private String title;
    private String categoryName;
    private Date createAt;
    private Long views;
    @TableField(exist = false)
    private List<String> tags;
    private Boolean isTop;
    private Boolean deleted;
}
