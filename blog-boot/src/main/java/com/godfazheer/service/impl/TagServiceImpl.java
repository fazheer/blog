package com.godfazheer.service.impl;

import com.godfazheer.dao.TagDao;
import com.godfazheer.entity.Tag;
import com.godfazheer.model.dto.TagCardDTO;
import com.godfazheer.model.dto.TagDTO;
import com.godfazheer.model.vo.TagVO;
import com.godfazheer.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List<TagDTO> getTags() {
        return tagDao.selectList();
    }

    @Override
    public List<String> getTagNamesByArticleId(Long articleId) {
        return tagDao.selectTagNameByArticleId(articleId);
    }

    @Override
    public void delTags(List<Long> tagIds) {
        tagDao.delArticleRel(tagIds);
        tagDao.deleteBatchIds(tagIds);
    }

    @Override
    public void updateTag(TagVO tagVO) {
        tagDao.updateById(Tag.builder()
                .id(tagVO.getId())
                .name(tagVO.getName())
                .build());
    }

    @Override
    public void saveTag(TagVO tagVO) {
        tagDao.insert(Tag.builder()
                .name(tagVO.getName())
                .build());
    }
    @Override
    public void buildRel(Long articleId,Long tagId){
        tagDao.addArticleTagRel(articleId,tagId);
    }
    @Override
    public List<TagCardDTO> getTagCard(Long pageNum) {
        return tagDao.selectTagCard((pageNum-1)*10);
    }
    @Override
    public List<TagCardDTO> getTagCardByName(String name ,Long pageNum) {
        return tagDao.selectTagCardByName(name,(pageNum-1)*10);
    }
    @Override
    public Long getTagCount() {
        return tagDao.selectCount(null);
    }

    @Override
    public Long getTagCardByNameCount(String name) {
        return tagDao.selectTagCardByNameCount(name);
    }
}
