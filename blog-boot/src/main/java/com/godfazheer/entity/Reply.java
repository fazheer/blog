package com.godfazheer.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 回复表
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Data
@Builder
@TableName("tbl_reply")
public class Reply implements Serializable {


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论者昵称
     */
    private String name;

    /**
     * 评论者邮箱
     */
    private String email;

    /**
     * 评论着网址
     */
    private String url;
    private Date createAt;

    /**
     * 点赞次数
     */
    private Integer love;

    /**
     * 设备名称
     */
    private String device;

    /**
     * 用户的ip地址
     */
    private String address;

    /**
     * 操作系统
     */
    private String ops;

    /**
     * 所示评论的id
     */
    private Long commentId;
    @TableField("to_id")
    private Long toId;
}
