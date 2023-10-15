package com.godfazheer.service.impl;

import com.godfazheer.dao.DescribeDao;
import com.godfazheer.entity.Describe;
import com.godfazheer.model.dto.DescribeDTO;
import com.godfazheer.model.vo.DescribeVO;
import com.godfazheer.service.DescribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Service
public class DescribeServiceImpl implements DescribeService {
    @Autowired
    DescribeDao describeDao;
    @Override
    public boolean saveDescribe(DescribeVO describeVO) {
        return describeDao.insert(Describe.builder()
                        .id(describeVO.getId())
                        .content(describeVO.getContent())
                .build())>0;
    }

    @Override
    public boolean delDescribe(Long id) {
        return describeDao.deleteById(id)>0;
    }

    @Override
    public boolean updateDescribe(DescribeVO describeVO) {
        return describeDao.updateById(Describe.builder()
                .id(describeVO.getId())
                .content(describeVO.getContent())
                .build())>0;
    }

    @Override
    public DescribeDTO getDescribe() {
        return describeDao.selectContent();
    }
}
