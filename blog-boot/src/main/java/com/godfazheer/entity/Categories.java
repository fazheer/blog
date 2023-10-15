package com.godfazheer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

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
@TableName("tbl_categories")
public class Categories implements Serializable {


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
}
