package com.godfazheer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfazheer.entity.Tag;
import com.godfazheer.model.dto.TagCardDTO;
import com.godfazheer.model.dto.TagDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface TagDao extends BaseMapper<Tag> {
    List<String> selectTagNameByArticleId(Long articleId);
    @Select("select * from tbl_tag")
    List<TagDTO> selectList();
    void addArticleTagRel(@Param("articleId") Long articleId,@Param("tagId") Long tagId);
    @Select("select count(*) from tbl_categories ")
    Long selectCategoryCount();
    List<TagCardDTO> selectTagCard(@Param("pageNum") Long pageNum);
    List<TagCardDTO> selectTagCardByName(@Param("name") String name,@Param("pageNum") Long pageNum);

    Long selectTagCardByNameCount(String name);

    void delArticleRel(List<Long> tagIds);
    @Select("select * from tbl_tag where name = #{name}")
    Tag selectOneTag(@Param("name") String name);
}
