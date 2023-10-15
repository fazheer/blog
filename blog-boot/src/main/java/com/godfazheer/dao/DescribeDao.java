package com.godfazheer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfazheer.entity.Describe;
import com.godfazheer.model.dto.DescribeDTO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface DescribeDao extends BaseMapper<Describe> {
    DescribeDTO selectContent();
}
