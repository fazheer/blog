package com.godfazheer.controller;

import com.godfazheer.model.dto.TagCardDTO;
import com.godfazheer.model.dto.TagDTO;
import com.godfazheer.model.vo.TagVO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.TagServiceImpl;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagServiceImpl tagService;
    @GetMapping
    @ResponseBody
    @PermitAll
    public ResultVO<?> getTags(){
        List<TagDTO> tags = tagService.getTags();
        return ResultVO.ok(tags);
    }
    @GetMapping("/card/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getTagCard(@PathVariable("pageNum") Long pageNum){
        List<TagCardDTO> tags = tagService.getTagCard(pageNum);
        return ResultVO.ok(tags);
    }
    @GetMapping("/card/name/{name}/page/{pageNum}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getTagCardByName(@PathVariable("name") String name,@PathVariable("pageNum") Long pageNum){
        List<TagCardDTO> tags = tagService.getTagCardByName(name,pageNum);
        return ResultVO.ok(tags);
    }
    @GetMapping("/card/name/{name}")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getTagCardByNameCount(@PathVariable("name") String name){
        Long count = tagService.getTagCardByNameCount(name);
        return ResultVO.ok(count);
    }
    @GetMapping("/card/count")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getTagCount(){
        Long count = tagService.getTagCount();
        return ResultVO.ok(count);
    }
    @PutMapping("/del")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:tag:del')")
    public ResultVO<?> delTags(@RequestBody List<Long> ids){
        tagService.delTags(ids);
        return ResultVO.ok();
    }
    @PutMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:tag:update')")
    public ResultVO<?> updateTag(@RequestBody TagVO tagVO) {
        tagService.updateTag(tagVO);
        return ResultVO.ok();
    }
    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:tag:save')")
    public ResultVO<?> saveTag(@RequestBody TagVO tagVO) {
        tagService.saveTag(tagVO);
        return ResultVO.ok();
    }
    @GetMapping("/tagId/{tagId}/articleId/{articleId}")
    @ResponseBody
    @PreAuthorize("hasAuthority('sys:tag:save')")
    public ResultVO<?> saveRel(@PathVariable("tagId") Long tagId,@PathVariable("articleId") Long articleId) {
        tagService.buildRel(articleId,tagId);
        return ResultVO.ok();
    }

}
