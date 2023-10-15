package com.godfazheer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Getter
@Setter
@TableName("sys_role")
public class Role implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String roleKey;
}
