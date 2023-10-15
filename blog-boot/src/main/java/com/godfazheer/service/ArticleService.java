package com.godfazheer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.model.dto.NeighborArticleDTO;
import com.godfazheer.model.dto.article.*;
import com.godfazheer.model.vo.ArticleVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface ArticleService {

    List<ArticleBaseDTO> getArchiveTitleArticle(String title,Long pageNum);

    Long getArchiveTitleArticleCount(String title);

    List<ArticleBaseYearDTO> getCategoryArticle(Long categoryId, Long pageNum);

    List<ArticleBaseYearDTO> getTagArticle(Long tagId, Long pageNum);

    Long getArticleCount();

    public List<ArticleHomeCardDTO> getHomeArticleCard(Long pageNum);

    void updateArticleView(Long articleId);

    public ArticleDTO getArticleById(Long articleId) throws JsonProcessingException;

    void saveArticle(ArticleVO articleVO) throws JsonProcessingException;

    public int deleteArticleById(Long id);

    ArticleAdminUpdateDTO getAdminArticleById(Long articleId) throws JsonProcessingException;

    List<ArticleAdminDTO> getAdminArticles(Long pageNum);

    List<ArticleAdminDTO> getAdminArticlesByCategoryId(Long categoryId, Long pageNum);

    List<ArticleAdminDTO> getAdminDelArticlesByCategoryId(Long categoryId, Long pageNum);

    List<ArticleAdminDTO> getAdminDraftArticlesByCategoryId(Long categoryId, Long pageNum);

    List<ArticleAdminDTO> getAdminArticlesByTagId(Long tagId, Long pageNum);

    List<ArticleAdminDTO> getAdminDelArticlesByTagId(Long tagId, Long pageNum);

    List<ArticleAdminDTO> getAdminDraftArticlesByTagId(Long tagId, Long pageNum);

    List<ArticleAdminDTO> getAdminArticlesByTitle(String title, Long pageNum);

    List<ArticleAdminDTO> getAdminDelArticlesByTitle(String title, Long pageNum);

    List<ArticleAdminDTO> getAdminDraftArticlesByTitle(String title, Long pageNum);

    List<ArticleAdminDTO> getDelArticles(Long pageNum);

    List<ArticleAdminDTO> getDraftArticles(Long pageNum);

    void setArticleIdsRedis() throws JsonProcessingException;

    void updateArticle(ArticleVO articleVO) throws JsonProcessingException;

    NeighborArticleDTO getPrevArticle(Long articleId);

    NeighborArticleDTO getNextArticle(Long articleId);

    public int deleteArticleByIds(List<Long> ids);


    public List<ArticleBaseDTO> getArticlesByTagId(Long classifyingId, Integer pageNum);

    int deleteArticlesPhysics(List<Long> ids);

    List<ArticleBaseYearDTO> getArchiveArticle(Long pageNum);

    void updateTop(Boolean isTop, Long id);

    List<ArticleRankDTO> getArticleViewRank();

    void recoveryDelArticle(Long id);

    Long getDraftArticlesCount();

    Long getDelArticlesCount();

    Long getArticleCountByTagId(Long tagId);

    Long getDraftArticleCountByTagId(Long tagId);

    Long getDelArticleCountByTagId(Long tagId);

    Long getArticleCountByCategoryId(Long categoryId);

    Long getDraftArticleCountByCategoryId(Long categoryId);

    Long getDelArticleCountByCategoryId(Long categoryId);
}
