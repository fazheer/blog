package com.godfazheer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.dao.ArticleDao;
import com.godfazheer.dao.CategoriesDao;
import com.godfazheer.dao.TagDao;
import com.godfazheer.entity.Article;
import com.godfazheer.entity.Categories;
import com.godfazheer.entity.Tag;
import com.godfazheer.model.dto.NeighborArticleDTO;
import com.godfazheer.model.dto.article.*;
import com.godfazheer.model.vo.ArticleVO;
import com.godfazheer.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private CategoriesDao categoriesDao;
    @Autowired
    private RedisServiceImpl redisService;


    @Override
    public List<ArticleBaseYearDTO> getArchiveArticle(Long pageNum) {
        return articleDao.selectArticleBaseYear((pageNum - 1) * 10);
    }

    @Override
    public List<ArticleBaseDTO> getArchiveTitleArticle(String title, Long pageNum) {
        return articleDao.selectArticleBaseWithTitle(title, (pageNum - 1) * 10);
    }

    @Override
    public Long getArchiveTitleArticleCount(String title) {
        return articleDao.selectArticleBaseWithTitleCount(title);
    }

    @Override
    public List<ArticleBaseYearDTO> getCategoryArticle(Long categoryId, Long pageNum) {
        return articleDao.selectArticleBaseYearByCategoryId(categoryId, (pageNum - 1) * 10);
    }

    @Override
    public List<ArticleBaseYearDTO> getTagArticle(Long tagId, Long pageNum) {
        return articleDao.selectArticleBaseYearByTagId(tagId, (pageNum - 1) * 10);
    }

    public List<ArticleBaseDTO> getTopSixArticle() {
        return articleDao.selectToxSixArticles();
    }

    @Override
    public Long getArticleCount() {
        return articleDao.selectArticleCount();
    }

    @Override
    public List<ArticleHomeCardDTO> getHomeArticleCard(Long pageNum) {
        return articleDao.selectHomeArticles((pageNum - 1) * 10);
    }

    @Override
    public void updateArticleView(Long articleId) {
        Article article = articleDao.selectById(articleId);
        LambdaUpdateWrapper<Article> luw = new LambdaUpdateWrapper<>();
        luw.eq(Article::getId, articleId);
        luw.set(Article::getViews, article.getViews() + 1);
        articleDao.update(null, luw);
    }

    @Override
    public ArticleDTO getArticleById(Long articleId) throws JsonProcessingException {
        setArticleIdsRedis();
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getId, articleId);
        Article article = articleDao.selectOne(lqw);
        return ArticleDTO.builder()
                .id(articleId)
                .title(article.getTitle())
                .coverUrl(article.getCoverUrl())
                .content(article.getContent())
                .createAt(article.getCreateAt())
                .views(article.getViews())
                .prevArticle(getPrevArticle(articleId))
                .nextArticle(getNextArticle(articleId))
                .build();
    }

    @Override
    public ArticleAdminUpdateDTO getAdminArticleById(Long id) {
        ArticleAdminUpdateDTO article = articleDao.selectAdminArticleById(id);
        article.setTags(tagDao.selectTagNameByArticleId(id));
        return article;
    }

    @Override
    public List<ArticleAdminDTO> getAdminArticles(Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminArticles((pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminArticlesByCategoryId(Long categoryId, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminArticlesByCategoryId(categoryId, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminDelArticlesByCategoryId(Long categoryId, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminDelArticlesByCategoryId(categoryId, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminDraftArticlesByCategoryId(Long categoryId, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminDraftArticlesByCategoryId(categoryId, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminArticlesByTagId(Long tagId, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminArticlesByTagId(tagId, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminDelArticlesByTagId(Long tagId, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminDelArticlesByTagId(tagId, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminDraftArticlesByTagId(Long tagId, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminDraftArticlesByTagId(tagId, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminArticlesByTitle(String title, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminArticlesByTitle(title, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminDelArticlesByTitle(String title, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminDelArticlesByTitle(title, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getAdminDraftArticlesByTitle(String title, Long pageNum) {
        List<ArticleAdminDTO> articleAdminDTOS = articleDao.selectAdminDraftArticlesByTitle(title, (pageNum - 1) * 10);
        articleAdminDTOS.forEach(articleAdminDTO -> articleAdminDTO.setTags(tagDao.selectTagNameByArticleId(articleAdminDTO.getId())));
        return articleAdminDTOS;
    }

    @Override
    public List<ArticleAdminDTO> getDelArticles(Long pageNum) {
        List<ArticleAdminDTO> delArticles = articleDao.selectDelArticles((pageNum - 1) * 10);
        delArticles.forEach(delArticle -> delArticle.setTags(tagDao.selectTagNameByArticleId(delArticle.getId())));
        return delArticles;
    }

    @Override
    public List<ArticleAdminDTO> getDraftArticles(Long pageNum) {
        List<ArticleAdminDTO> draftArticles = articleDao.selectDraftArticles((pageNum - 1) * 10);
        draftArticles.forEach(draftArticle -> draftArticle.setTags(tagDao.selectTagNameByArticleId(draftArticle.getId())));
        return draftArticles;
    }

    @Override
    public void setArticleIdsRedis() throws JsonProcessingException {
        List<Long> articleIds = articleDao.selectArticleIds();
        redisService.setListToZSet("article:ids", articleIds);
    }

    @Override
    public void updateArticle(ArticleVO articleVO) {
        articleDao.updateById(Article.builder()
                .id(articleVO.getId())
                .title(articleVO.getTitle())
                .content(articleVO.getContent())
                .coverUrl(articleVO.getCoverUrl())
                .categoriesId(getCategoryId(articleVO.getCategoryName()))
                .status(articleVO.getStatus())
                .isTop(articleVO.getIsTop())
                .createAt(articleVO.getCreateAt())
                .build());
        buildArticleTag(articleVO.getId(),articleVO.getTags());
    }

    public Long getCategoryId(String name) {
        if(name==null) {
            return null;
        }
        LambdaQueryWrapper<Categories> clqw = new LambdaQueryWrapper<Categories>()
                .eq(Categories::getName, name);
        Categories category = categoriesDao.selectOne(clqw);
        if (Objects.isNull(category)) {
            categoriesDao.insert(Categories.builder()
                    .name(name)
                    .build());
            category = categoriesDao.selectOne(clqw);
        }
        return category.getId();
    }

    public void buildArticleTag(Long articleId, List<String> tags) {
        if(tags==null) {
            return;
        }
        tags.forEach(tag -> {
            Tag selectTag = tagDao.selectOneTag(tag);
            if (Objects.isNull(selectTag)) {
                tagDao.insert(Tag.builder()
                        .name(tag)
                        .build());
                selectTag = tagDao.selectOneTag(tag);
            }
        try {
            tagDao.addArticleTagRel(articleId, selectTag.getId());
        }catch (Exception e) {
        }
        });

    }

    @Override
    public void saveArticle(ArticleVO articleVO) {
        articleDao.insert(
                Article.builder()
                        .title(articleVO.getTitle())
                        .content(articleVO.getContent())
                        .coverUrl(articleVO.getCoverUrl())
                        .categoriesId(getCategoryId(articleVO.getCategoryName()))
                        .status(articleVO.getStatus())
                        .isTop(articleVO.getIsTop())
                        .createAt(articleVO.getCreateAt())
                        .build()
        );

        Article savedArticle = articleDao.selectOne(new LambdaQueryWrapper<Article>()
                .eq(Article::getTitle, articleVO.getTitle()));
        buildArticleTag(savedArticle.getId(), articleVO.getTags());
    }

    @Override
    public int deleteArticleById(Long id) {
        return articleDao.deleteById(id);
    }

    @Override
    public List<ArticleBaseDTO> getArticlesByTagId(Long tagId, Integer pageNum) {
        return articleDao.selectArticleByTagId(tagId, (pageNum - 1) * 10);
    }


    @Override
    public NeighborArticleDTO getPrevArticle(Long articleId) {
        Long rank = redisService.getZSetRank("article:ids", String.valueOf(articleId));
        if (rank == 0) return null;
        Set<String> range = redisService.getZSetRangeByRank("article:ids", rank - 1);
        String next = range.iterator().next();
        Long prevArticleId = Long.parseLong(next);
        return articleDao.selectNeighborArticleById(prevArticleId);
    }

    @Override
    public NeighborArticleDTO getNextArticle(Long articleId) {
        Long rank = redisService.getZSetRank("article:ids", String.valueOf(articleId));
        Long size = redisService.getZSetSize("article:ids");
        if (Objects.equals(rank, size - 1)) return null;
        Set<String> range = redisService.getZSetRangeByRank("article:ids", rank + 1);
        String next = range.iterator().next();
        Long nextArticleId = Long.parseLong(next);
        return articleDao.selectNeighborArticleById(nextArticleId);
    }


    @Override
    public int deleteArticleByIds(List<Long> ids) {
        return articleDao.deleteBatchIds(ids);
    }

    @Override
    public int deleteArticlesPhysics(List<Long> ids) {
        return articleDao.deleteArticlesPhysics(ids);
    }

    @Override
    public void updateTop(Boolean isTop, Long id) {
        articleDao.updateTop(isTop, id);
    }

    @Override
    public List<ArticleRankDTO> getArticleViewRank() {
        return articleDao.selectArticleRank();
    }

    @Override
    public void recoveryDelArticle(Long id) {
        articleDao.updateDelete(id);
    }

    @Override
    public Long getDraftArticlesCount() {
        return articleDao.selectDraftArticleCount();
    }

    @Override
    public Long getDelArticlesCount() {
        return articleDao.selectDelArticleCount();
    }

    @Override
    public Long getArticleCountByTagId(Long tagId) {
        return articleDao.selectArticleCountByTagId(tagId);
    }

    @Override
    public Long getDraftArticleCountByTagId(Long tagId) {
        return articleDao.selectDraftArticleCountByTagId(tagId);
    }

    @Override
    public Long getDelArticleCountByTagId(Long tagId) {
        return articleDao.selectDelArticleCountByTagId(tagId);
    }

    @Override
    public Long getArticleCountByCategoryId(Long categoryId) {
        return articleDao.selectAdminArticlesByCategoryIdCount(categoryId);
    }

    @Override
    public Long getDraftArticleCountByCategoryId(Long categoryId) {
        return articleDao.selectAdminDraftArticlesByCategoryIdCount(categoryId);
    }

    @Override
    public Long getDelArticleCountByCategoryId(Long categoryId) {
        return articleDao.selectAdminDelArticlesByCategoryIdCount(categoryId);
    }

    public Long getArticleCountByTitle(String title) {
        return articleDao.selectArticleCountByTitle(title);
    }

    public Long getDelArticleCountByTitle(String title) {
        return articleDao.selectDelArticleCountByTitle(title);
    }

    public Long getDraftArticleCountByTitle(String title) {
        return articleDao.selectDraftArticleCountByTitle(title);
    }
}
