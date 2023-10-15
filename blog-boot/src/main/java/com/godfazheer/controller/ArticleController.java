package com.godfazheer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.model.dto.article.*;
import com.godfazheer.model.vo.ArticleVO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.ArticleServiceImpl;
import com.godfazheer.service.impl.RedisServiceImpl;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private RedisServiceImpl redisService;

    @GetMapping("/home/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getHomeArticleCard(@PathVariable("pageNum") Long pageNum) {
        //查询首页文章
        List<ArticleHomeCardDTO> homeArticleCard = articleService.getHomeArticleCard(pageNum);
        redisService.incrValue("sys:view");
        return ResultVO.ok(homeArticleCard);
    }

    @GetMapping("/home")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getCardInfo() {
        return ResultVO.ok();
    }

    @GetMapping("/top")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getTopSixArticleCard() {
        //查询首页文章
        List<ArticleBaseDTO> topSixArticle = articleService.getTopSixArticle();
        return ResultVO.ok(topSixArticle);
    }


    @GetMapping("/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArticle(@PathVariable("id") Long id) throws JsonProcessingException {
        //id查询文章内容
        ArticleDTO article = articleService.getArticleById(id);
        articleService.updateArticleView(article.getId());
        return ResultVO.ok(article);
    }


    @GetMapping("/categories/{id}/pageNum/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArticleByCategoryID(@PathVariable("id") Long id, @PathVariable("pageNum") Long pageNum) {
        //根据分类id查询文章内容
        List<ArticleBaseYearDTO> article = articleService.getCategoryArticle(id, pageNum);
        return ResultVO.ok(article);
    }
    @GetMapping("/tag/{id}/pageNum/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArticleByTagID(@PathVariable("id") Long id, @PathVariable("pageNum") Long pageNum) {
        //根据分类id查询文章内容
        List<ArticleBaseYearDTO> article = articleService.getTagArticle(id, pageNum);
        return ResultVO.ok(article);
    }

    @GetMapping
    @ResponseBody
    @PermitAll
    public ResultVO<?> getCount() {
        return ResultVO.ok(articleService.getArticleCount());
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArticleCountByCategoryId(@PathVariable("id") Long id) {
        Long count = articleService.getArticleCountByCategoryId(id);
        return ResultVO.ok(count);
    }

    @GetMapping("/tag/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArticleCountByTagId(@PathVariable("id") Long id) {
        Long count = articleService.getArticleCountByTagId(id);
        return ResultVO.ok(count);
    }

    @GetMapping("/title/{title}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArticleCountByTitle(@PathVariable("title") String title) {
        Long count = articleService.getArticleCountByTitle(title);
        return ResultVO.ok(count);
    }

    @GetMapping("/archive/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArchiveArticle(@PathVariable("pageNum") Long pageNum) {
        List<ArticleBaseYearDTO> article = articleService.getArchiveArticle(pageNum);
        return ResultVO.ok(article);
    }

    @GetMapping("/archive/title/{title}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArchiveTitleArticle(@PathVariable("title") String title, @PathVariable("pageNum") Long pageNum) {
        List<ArticleBaseDTO> article = articleService.getArchiveTitleArticle(title, pageNum);
        return ResultVO.ok(article);
    }

    @GetMapping("/archive/title/{title}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArchiveTitleArticleCount(@PathVariable("title") String title) {
        Long count = articleService.getArchiveTitleArticleCount(title);
        return ResultVO.ok(count);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:article:save')")
    public ResultVO<?> saveArticle(@RequestBody ArticleVO articleVO) {
        articleService.saveArticle(articleVO);
        return ResultVO.ok();
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:article:update')")
    public ResultVO<?> updateArticle(@RequestBody ArticleVO articleVO) {
        articleService.updateArticle(articleVO);
        return ResultVO.ok();
    }

    @GetMapping("/admin/id/{id}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminArticleById(@PathVariable("id") Long id) throws JsonProcessingException {
        ArticleAdminUpdateDTO article = articleService.getAdminArticleById(id);
        return ResultVO.ok(article);
    }

    @GetMapping("/admin/del/{pageNum}")
    @ResponseBody
    public ResultVO<?> getDelArticles(@PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> delArticles = articleService.getDelArticles(pageNum);
        return ResultVO.ok(delArticles);
    }

    @GetMapping("/admin/draft/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getDraftArticles(@PathVariable("pageNum") Long pageNum) {
        //查询草稿内容
        List<ArticleAdminDTO> draftArticles = articleService.getDraftArticles(pageNum);
        return ResultVO.ok(draftArticles);
    }

    @DeleteMapping("/admin/del/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:article:del')")
    public ResultVO<?> delArticle(@PathVariable("id") Long id) {
        int i = articleService.deleteArticleById(id);
        return ResultVO.ok(i);
    }

    @PutMapping("/admin/del")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:article:del')")
    public ResultVO<?> delArticles(@RequestBody List<Long> ids) {
        int i = articleService.deleteArticleByIds(ids);
        return ResultVO.ok(i);
    }

    @PutMapping("/admin/del/physics")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:article:del')")
    public ResultVO<?> delArticlePhysics(@RequestBody List<Long> ids) {
        //永久删除回收站里的文章
        int i = articleService.deleteArticlesPhysics(ids);
        return ResultVO.ok(i);
    }

    @GetMapping("/admin/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminArticle(@PathVariable("pageNum") Long pageNum) {
        //后台页面的文章内容
        List<ArticleAdminDTO> adminArticles = articleService.getAdminArticles(pageNum);
        return ResultVO.ok(adminArticles);
    }


    @GetMapping("/admin/del")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDelArticleCount() {
        Long count = articleService.getDelArticlesCount();
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/draft")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDraftArticleCount() {
        Long count = articleService.getDraftArticlesCount();
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/tag/{tagId}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminArticleCountByTagId(@PathVariable("tagId") Long tagId) {
        Long count = articleService.getArticleCountByTagId(tagId);
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/del/tag/{tagId}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDelArticleCountByTagId(@PathVariable("tagId") Long tagId) {
        Long count = articleService.getDelArticleCountByTagId(tagId);
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/draft/tag/{tagId}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDraftArticleCountByTagId(@PathVariable("tagId") Long tagId) {
        Long count = articleService.getDraftArticleCountByTagId(tagId);
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/del/category/{categoryId}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getDelArticleCountByCategoryId(@PathVariable("categoryId") Long categoryId) {
        Long count = articleService.getDelArticleCountByCategoryId(categoryId);
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/draft/category/{categoryId}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDraftArticleCountByCategoryId(@PathVariable("categoryId") Long categoryId) {
        Long count = articleService.getDraftArticleCountByCategoryId(categoryId);
        return ResultVO.ok(count);
    }


    @GetMapping("/admin/draft/title/{title}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDraftArticleCountByTitle(@PathVariable("title") String title) {
        Long count = articleService.getDraftArticleCountByTitle(title);
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/del/title/{title}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDelArticleCountByTitle(@PathVariable("title") String title) {
        Long count = articleService.getDelArticleCountByTitle(title);
        return ResultVO.ok(count);
    }

    @GetMapping("/admin/category/{categoryId}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminArticleByCategoryId(@PathVariable("categoryId") Long categoryId, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminArticlesByCategoryId(categoryId, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/del/category/{categoryId}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDelArticleByCategoryId(@PathVariable("categoryId") Long categoryId, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminDelArticlesByCategoryId(categoryId, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/draft/category/{categoryId}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDraftArticleByCategoryId(@PathVariable("categoryId") Long categoryId, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminDraftArticlesByCategoryId(categoryId, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/tag/{tagId}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminArticleByTagId(@PathVariable("tagId") Long tagId, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminArticlesByTagId(tagId, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/draft/tag/{tagId}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDraftArticleByTagId(@PathVariable("tagId") Long tagId, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminDraftArticlesByTagId(tagId, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/del/tag/{tagId}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDelArticleByTagId(@PathVariable("tagId") Long tagId, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminDelArticlesByTagId(tagId, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/title/{title}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminArticleByTitle(@PathVariable("title") String title, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminArticlesByTitle(title, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/draft/title/{title}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDraftArticleByTitle(@PathVariable("title") String title, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminDraftArticlesByTitle(title, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/del/title/{title}/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminDelArticleByTitle(@PathVariable("title") String title, @PathVariable("pageNum") Long pageNum) {
        List<ArticleAdminDTO> adminArticles = articleService.getAdminDelArticlesByTitle(title, pageNum);
        return ResultVO.ok(adminArticles);
    }

    @GetMapping("/admin/del/recovery/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:article:update')")
    public ResultVO<?> recoveryDelArticle(@PathVariable("id") Long id) {
        articleService.recoveryDelArticle(id);
        return ResultVO.ok();
    }

    @GetMapping("/isTop/{isTop}/id/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:article:update')")
    public ResultVO<?> updateArticleTop(@PathVariable("isTop") Boolean isTop, @PathVariable("id") Long id) {
        articleService.updateTop(isTop, id);
        return ResultVO.ok();
    }

    @GetMapping("/rank")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArticleRank() {
        List<ArticleRankDTO> articleViewRank = articleService.getArticleViewRank();
        return ResultVO.ok(articleViewRank);
    }
}
