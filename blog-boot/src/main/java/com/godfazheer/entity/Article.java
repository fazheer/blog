package com.godfazheer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_article")
public class Article implements Serializable {


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String title;

    private String coverUrl;

    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createAt;

    private Long categoriesId;

    private Integer status;

    private Boolean isTop;
    private Long views;
    @TableLogic
    private Boolean deleted;
}
