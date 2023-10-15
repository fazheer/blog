package com.godfazheer.service.impl;

import com.godfazheer.dao.CommentDao;
import com.godfazheer.dao.ReplyDao;
import com.godfazheer.entity.Comment;
import com.godfazheer.entity.Reply;
import com.godfazheer.model.dto.NameUrlDTO;
import com.godfazheer.model.dto.comment.*;
import com.godfazheer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ReplyDao replyDao;

    public List<CommentReplyDTO> getPageComment(Long pageId) {
        List<CommentDTO> commentDTOS = commentDao.selectPageComments(pageId);
        List<CommentReplyDTO> commentReplyDTOList = new ArrayList<>();
        commentDTOS.forEach(commentDTO -> {
            List<ReplyDTO> replyDTOS = commentDao.selectReplies(commentDTO.getId());
            commentReplyDTOList.add(new CommentReplyDTO(commentDTO, replyDTOS));
        });
        return commentReplyDTOList;
    }
    public List<ReplyDTO> getReplyByCommentId(Long id) {
        return commentDao.selectReplies(id);
    }

    @Override
    public int updateCommentLove(Long commentId) {
        return commentDao.updateCommentLove(commentId);
    }

    @Override
    public int updateReplyLove(Long replyId) {
        return commentDao.updateReplyLove(replyId);
    }

    public int saveComment(CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .name(commentDTO.getName())
                .email(commentDTO.getEmail())
                .ops(commentDTO.getOps())
                .device(commentDTO.getDevice())
                .address(commentDTO.getAddress())
                .content(commentDTO.getContent())
                .url(commentDTO.getUrl())
                .createAt(commentDTO.getCreateAt())
                .pageId(commentDTO.getPageId())
                .build();
        return commentDao.insert(comment);
    }

    @Override
    public NameUrlDTO getReplyUrl(Long id) {
        return commentDao.selectReplyUrl(id);
    }

    public int saveReply(ReplyDTO replyDTO) {
        Reply reply = Reply.builder()
                .name(replyDTO.getName())
                .email(replyDTO.getUrl())
                .url(replyDTO.getUrl())
                .content(replyDTO.getContent())
                .createAt(replyDTO.getCreateAt())
                .address(replyDTO.getAddress())
                .ops(replyDTO.getOps())
                .device(replyDTO.getDevice())
                .toId(replyDTO.getToId())
                .commentId(replyDTO.getCommentId())
                .build();
        return replyDao.insert(reply);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentDao.deleteById(id);
        replyDao.deleteById(id);

    }

    @Override
    public void deleteCommentByIds(List<Long> ids) {
        commentDao.deleteBatchIds(ids);
        replyDao.deleteBatchIds(ids);
    }

    @Override
    public List<CommentAdminDTO> listComments(Long pageNum) {
        return commentDao.selectAllCommentAndReply((pageNum-1)*10);
    }
    @Override
    public Long getAllCount() {
        return commentDao.selectAllCommentAndReplyCount();
    }
    @Override
    public CommentCountDTO getCommentsMonthCount() {
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        return commentDao.selectMonthCommentCount("0"+String.valueOf(month));
    }
}
