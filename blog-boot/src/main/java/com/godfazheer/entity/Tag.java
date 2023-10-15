package com.godfazheer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_tag")
public class Tag implements Serializable {


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

}
