package com.godfazheer.service;

import com.godfazheer.model.dto.NameUrlDTO;
import com.godfazheer.model.dto.comment.*;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface CommentService{
    public List<CommentReplyDTO> getPageComment(Long pageId);
    public int saveComment(CommentDTO commentVO);

    NameUrlDTO getReplyUrl(Long id);

    public int saveReply(ReplyDTO replyDTO);
    public int updateCommentLove(Long commentId);
    public int updateReplyLove(Long replyId);

    void deleteCommentById(Long id);

    void deleteCommentByIds(List<Long> ids);

    List<CommentAdminDTO> listComments(Long pageNum);

    Long getAllCount();

    CommentCountDTO getCommentsMonthCount();
}
