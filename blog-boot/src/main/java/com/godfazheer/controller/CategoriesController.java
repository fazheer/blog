package com.godfazheer.controller;

import com.godfazheer.model.dto.CategoryCardDTO;
import com.godfazheer.model.dto.ArchiveCardDTO;
import com.godfazheer.model.dto.CategoriesDTO;
import com.godfazheer.model.vo.CategoriesVO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.CategoriesServiceImpl;
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
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesServiceImpl categoriesService;

    @GetMapping
    @ResponseBody
    @PermitAll
    public ResultVO<?> getCategories() {
        List<CategoriesDTO> categoriesDTOS = categoriesService.getCategories();
        return ResultVO.ok(categoriesDTOS);
    }
    @GetMapping("/card")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getCategoryCard() {
        List<CategoryCardDTO> categoryCardDTOS = categoriesService.getCategoryCard();
        return ResultVO.ok(categoryCardDTOS);
    }
    @GetMapping("/card/count")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getCategoryCardCount() {
        Long count = categoriesService.getCategoryCardCount();
        return ResultVO.ok(count);
    }
    @GetMapping("/card/admin/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminCategoryCard(@PathVariable("pageNum") Long pageNum) {
        List<CategoryCardDTO> categoryCardDTOS = categoriesService.getAdminCategoryCard(pageNum);
        return ResultVO.ok(categoryCardDTOS);
    }
    @GetMapping("/card/admin/name/{name}/page/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminCategoryCardByName(@PathVariable("name") String name,@PathVariable("pageNum") Long pageNum) {
        List<CategoryCardDTO> categoryCardDTOS = categoriesService.getAdminCategoryCardByName(name,pageNum);
        return ResultVO.ok(categoryCardDTOS);
    } @GetMapping("/card/admin/name/{name}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAdminCategoryCardCountByName(@PathVariable("name") String name) {
        Long count = categoriesService.getAdminCategoryCardByNameCount(name);
        return ResultVO.ok(count);
    }
    @GetMapping("/archive")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getArchiveCard() {
        List<ArchiveCardDTO> archiveCard = categoriesService.getArchiveCard();
        return ResultVO.ok(archiveCard);
    }

    @PutMapping("/del")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:category:del')")
    public ResultVO<?> delCategories(@RequestBody List<Long> ids) {
        categoriesService.delCategories(ids);
        return ResultVO.ok();
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:category:save')")
    public ResultVO<?> saveCategories(@RequestBody CategoriesVO categoriesVO) {
        categoriesService.saveCategories(categoriesVO);
        return ResultVO.ok();
    }

    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:category:update')")
    public ResultVO<?> updateCategories(@RequestBody CategoriesVO categoriesVO) {
        categoriesService.updateCategories(categoriesVO);
        return ResultVO.ok();
    }
}
