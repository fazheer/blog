package com.godfazheer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfazheer.entity.Comment;
import com.godfazheer.model.dto.NameUrlDTO;
import com.godfazheer.model.dto.comment.CommentAdminDTO;
import com.godfazheer.model.dto.comment.CommentCountDTO;
import com.godfazheer.model.dto.comment.CommentDTO;
import com.godfazheer.model.dto.comment.ReplyDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface CommentDao extends BaseMapper<Comment> {
    List<CommentDTO> selectPageComments(@Param("pageId") Long pageId);

    List<ReplyDTO> selectReplies(@Param("commentID") Long commentId);

    List<CommentAdminDTO> selectAllCommentAndReply(@Param("pageNum") Long pageNum);

    @Select("select name,url from tbl_reply where id=#{id} limit 0,1")
    NameUrlDTO selectReplyUrl(@Param("id") Long id);

    @Update("update tbl_comment set love=love+1 where id=#{id}")
    Integer updateCommentLove(@Param("id") Long id);

    @Update("update tbl_reply set love=love+1 where id=#{id}")
    Integer updateReplyLove(@Param("id") Long id);
    CommentCountDTO selectMonthCommentCount(@Param("month") String month);
    Long selectAllCommentAndReplyCount();
}
