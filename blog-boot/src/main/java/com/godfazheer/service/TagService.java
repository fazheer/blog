package com.godfazheer.service;

import com.godfazheer.model.dto.TagCardDTO;
import com.godfazheer.model.dto.TagDTO;
import com.godfazheer.model.vo.TagVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface TagService  {


    List<TagDTO> getTags();

    List<String> getTagNamesByArticleId(Long articleId);


    void delTags(List<Long> tagIds);

    void updateTag(TagVO tagVO);

    void saveTag(TagVO tagVO);

    void buildRel(Long articleId, Long tagId);

    List<TagCardDTO> getTagCard(Long pageNum);

    List<TagCardDTO> getTagCardByName(String name, Long pageNum);

    Long getTagCount();

    Long getTagCardByNameCount(String name);
}
