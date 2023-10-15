package com.godfazheer.service;

import com.godfazheer.model.dto.DescribeDTO;
import com.godfazheer.model.vo.DescribeVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface DescribeService{
    boolean saveDescribe(DescribeVO noticeVO);

    boolean delDescribe(Long id);

    boolean updateDescribe(DescribeVO describeVO);

    DescribeDTO getDescribe();

}
