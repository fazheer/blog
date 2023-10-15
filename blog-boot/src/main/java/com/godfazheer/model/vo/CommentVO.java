package com.godfazheer.model.vo;

import lombok.Data;

@Data
public class CommentVO {
    private String content;
    private String name;
    private String email;
    private String url;
    private Long pageId;
}
