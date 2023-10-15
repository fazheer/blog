package com.godfazheer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfazheer.entity.Categories;
import com.godfazheer.model.CategoryCardDTO;
import com.godfazheer.model.dto.ArchiveCardDTO;
import com.godfazheer.model.dto.CategoriesDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface CategoriesDao extends BaseMapper<Categories> {
    @Select("select id,name from tbl_categories")
    List<CategoriesDTO> selectAll();

    @Select("select tc.id, name,count(ta.id) count from tbl_categories tc left join tbl_article ta on tc.id = ta.categories_id group by tc.id, name")
    List<CategoryCardDTO> selectHomeCategoryCard();

    @Select("select DATE_FORMAT(create_at,'%Y年%m月') as title,count(*) count from  tbl_article group by  DATE_FORMAT(create_at,'%Y年%m月')")
    List<ArchiveCardDTO> selectHomeArchiveCard();

    List<CategoryCardDTO> selectAdminCategoryCard(@Param("pageNum") Long pageNum);
    List<CategoryCardDTO> selectAdminCategoryCardByName(@Param("name") String name , @Param("pageNum") Long pageNum);

    Long selectAdminCategoryCardByNameCount(String name);
}
