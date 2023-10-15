package com.godfazheer.controller;

import com.godfazheer.model.dto.NameUrlDTO;
import com.godfazheer.model.dto.comment.*;
import com.godfazheer.model.vo.CommentVO;
import com.godfazheer.model.vo.ReplyVO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.CommentServiceImpl;
import com.godfazheer.utils.IPUtils;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    @Resource
    private HttpServletRequest httpServletRequest;

    @GetMapping("/{pageId}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getPageComment(@PathVariable("pageId") Long pageId) {
        List<CommentReplyDTO> pageComment = commentService.getPageComment(pageId);
        return ResultVO.ok(pageComment);
    }
    @GetMapping("/admin/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminComment(@PathVariable("pageNum") Long pageNum) {
        List<CommentAdminDTO> allComments = commentService.listComments(pageNum);
        return ResultVO.ok(allComments);
    }
    @GetMapping("/admin/count")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminCommentCount() {
        Long Count = commentService.getAllCount();
        return ResultVO.ok(Count);
    }
    @GetMapping("/url/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getReplyNameUrl(@PathVariable("id") Long id) {
        NameUrlDTO result = commentService.getReplyUrl(id);
        return ResultVO.ok(result);
    }
    @PostMapping("/save/comment")
    @ResponseBody
    @PermitAll
    public ResultVO<?> saveComment(@RequestBody CommentVO commentVO) throws Exception {
        String[] split = IPUtils.getIpBrowserOps(httpServletRequest);
        CommentDTO commentDTO = CommentDTO.builder()
                .name(commentVO.getName())
                .email(commentVO.getEmail())
                .url(commentVO.getUrl())
                .content(commentVO.getContent())
                .pageId(commentVO.getPageId())
                .address(split[0])
                .device(split[1])
                .ops(split[2])
                .createAt(new Date())
                .build();
        commentService.saveComment(commentDTO);
        return ResultVO.ok();
    }
    @PostMapping("/save/reply")
    @PermitAll
    public ResultVO<?> saveReply(@RequestBody ReplyVO replyVO) throws Exception {
        String[] split =IPUtils.getIpBrowserOps(httpServletRequest);
        replyVO.setDevice(split[0]);
        replyVO.setOps(split[1]);
        ReplyDTO replyDTO = ReplyDTO.builder()
                .name(replyVO.getName())
                .email(replyVO.getEmail())
                .url(replyVO.getUrl())
                .content(replyVO.getContent())
                .commentId(replyVO.getCommentId())
                .toId(replyVO.getToId())
                .address(split[0])
                .device(split[1])
                .ops(split[2])
                .createAt(new Date())
                .build();
        commentService.saveReply(replyDTO);
        return ResultVO.ok();
    }
    @GetMapping("/reply/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getReplyByCommentId(@PathVariable("id") Long id){
        List<ReplyDTO> replies = commentService.getReplyByCommentId(id);
        return ResultVO.ok(replies);
    }@GetMapping("/reply/love/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> updateReplyLove(@PathVariable("id") Long id){
        Integer result = commentService.updateReplyLove(id);
        return ResultVO.ok(result);
    }
    @GetMapping("/comment/love/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> updateCommentLove(@PathVariable("id") Long id){
        int result = commentService.updateCommentLove(id);
        return ResultVO.ok(result);
    }
    @GetMapping("/month")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getMonthCount(){
        return ResultVO.ok();
    }
    @DeleteMapping("/del/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:comment:del')")
    public ResultVO<?> deleteCommentOrReply(@PathVariable("id") Long id){
        commentService.deleteCommentById(id);
        return ResultVO.ok();
    }
    @PutMapping("/del")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:comment:del')")
    public ResultVO<?> deleteCommentOrReply(@RequestBody List<Long> ids){
        commentService.deleteCommentByIds(ids);
        return ResultVO.ok();
    }
    @GetMapping("/count")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getCommentCount( ){
        CommentCountDTO commentsMonthCount = commentService.getCommentsMonthCount();
        return ResultVO.ok(commentsMonthCount);
    }
}
