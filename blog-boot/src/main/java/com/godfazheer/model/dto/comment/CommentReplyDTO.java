package com.godfazheer.model.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class CommentReplyDTO {
    private CommentDTO comment;
    private List<ReplyDTO> replies;
}
