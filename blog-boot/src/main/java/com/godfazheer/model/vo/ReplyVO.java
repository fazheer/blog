package com.godfazheer.model.vo;

import lombok.Data;

@Data
public class ReplyVO {

    private String name;
    private String email;
    private String url;
    private String content;
    private String device;
    private String address;
    private String ops;
    private Long commentId;
    private Long toId;
}
