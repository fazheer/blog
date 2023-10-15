package com.godfazheer.model.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long id;


    private String content;


    private String name;


    private String email;

    private String url;

    private Date createAt;
    private Long love;

    private String device;


    private String address;

    private String ops;

    private Long commentId;

    private Long toId;
}
