 package com.godfazheer.dao;

 import com.baomidou.mybatisplus.core.mapper.BaseMapper;
 import com.godfazheer.entity.Article;
 import com.godfazheer.model.dto.NeighborArticleDTO;
 import com.godfazheer.model.dto.article.*;
 import org.apache.ibatis.annotations.Param;
 import org.apache.ibatis.annotations.Select;
 import org.apache.ibatis.annotations.Update;

 import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface ArticleDao extends BaseMapper<Article> {
    List<ArticleHomeCardDTO> selectHomeArticles(@Param("pageNum") Long pageNum);

    NeighborArticleDTO selectNeighborArticleById(@Param("articleId") Long articleId);


    @Select("select id,title,cover_url,create_at from tbl_article where deleted=0 order by create_at limit 0,6 ")
    List<ArticleBaseDTO> selectToxSixArticles();
    List<ArticleBaseDTO> selectArticleByTagId(@Param("tagId") Long tagId, Integer pageNum);

    List<Long> selectArticleIds();
    List<ArticleAdminDTO> selectAdminArticles(@Param("pageNum") Long pageNum);
    ArticleAdminUpdateDTO selectAdminArticleById(@Param("id") Long id);
    List<ArticleAdminDTO> selectDelArticles(@Param("pageNum") Long pageNum);
    List<ArticleAdminDTO> selectDraftArticles(@Param("pageNum") Long pageNum);

    int deleteArticlesPhysics(@Param("ids") List<Long> ids);

    List<String> selectGroupYear();
    List<ArticleBaseYearDTO> selectArticleBaseYear(Long pageNum);
    List<ArticleBaseYearDTO> selectArticleBaseYearByCategoryId(@Param("categoryId") Long categoryId,@Param("pageNum") Long pageNum);
    List<ArticleBaseYearDTO> selectArticleBaseYearByTagId(@Param("tagId") Long tgaId,@Param("pageNum") Long pageNum);
    Long selectArticleCountByTagId(@Param("tagId") Long tagId);
    Integer selectArticleCountByCategoryId(@Param("tagId") Long categoryId);

    List<ArticleBaseDTO> selectArticleBaseWithTitle(@Param("title") String title,@Param("pageNum") Long pageNum);
    Long selectArticleBaseWithTitleCount(@Param("title")String title);
    void updateTop(@Param("isTop") Boolean isTop,@Param("id") Long id);
    @Select("select views,title from tbl_article where deleted=0  order by views desc limit 0,7")
    List<ArticleRankDTO> selectArticleRank();

    @Update("UPDATE tbl_article SET deleted=0 WHERE id=#{id}")
    void updateDelete(Long id);
    List<ArticleAdminDTO> selectAdminArticlesByCategoryId(@Param("categoryId") Long categoryId, @Param("pageNum") Long pageNum);
    List<ArticleAdminDTO> selectAdminDraftArticlesByCategoryId(@Param("categoryId") Long categoryId, @Param("pageNum") Long pageNum);
    List<ArticleAdminDTO> selectAdminDelArticlesByCategoryId(@Param("categoryId") Long categoryId, @Param("pageNum") Long pageNum);
    Long selectAdminArticlesByCategoryIdCount(@Param("categoryId") Long categoryId);
    Long selectAdminDraftArticlesByCategoryIdCount(@Param("categoryId") Long categoryId);
    Long selectAdminDelArticlesByCategoryIdCount(@Param("categoryId") Long categoryId);
    List<ArticleAdminDTO> selectAdminArticlesByTagId(@Param("tagId") Long tagId, @Param("pageNum") Long pageNum);
    List<ArticleAdminDTO> selectAdminDraftArticlesByTagId(@Param("tagId") Long tagId, @Param("pageNum") Long pageNum);
    List<ArticleAdminDTO> selectAdminDelArticlesByTagId(@Param("tagId") Long tagId, @Param("pageNum") Long pageNum);
    Long selectDraftArticleCountByTagId(Long tagId);
    Long selectDelArticleCountByTagId(Long tagId);
    List<ArticleAdminDTO> selectAdminArticlesByTitle(@Param("title") String title, @Param("pageNum") Long pageNum);
    List<ArticleAdminDTO> selectAdminDelArticlesByTitle(@Param("title") String title, @Param("pageNum") Long pageNum);
    List<ArticleAdminDTO> selectAdminDraftArticlesByTitle(@Param("title") String title, @Param("pageNum") Long pageNum);
    Long selectArticleCountByTitle(@Param("title") String title);
    Long selectDelArticleCountByTitle(@Param("title") String title);
    Long selectDraftArticleCountByTitle(@Param("title") String title);
    Long selectArticleCount();
    Long selectDraftArticleCount();
    Long selectDelArticleCount();
}
