package com.godfazheer.model.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentAdminDTO {
    private Long id;
    private String content;
    private String name;
    private String email;
    private String url;
    private Date createAt;
    private Long love;
}
