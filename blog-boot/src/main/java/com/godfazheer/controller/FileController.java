package com.godfazheer.controller;

import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.ObsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private ObsServiceImpl obsService;
    @ResponseBody
    @PostMapping("/img")
    @PreAuthorize("hasAuthority('sys:article:save')")
    public ResultVO<?> uploadPostImg(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        String path = obsService.uploadPostImg(multipartFile);
        return ResultVO.ok(path);
    }
    @ResponseBody
    @PostMapping("/avatar")
    @PreAuthorize("hasAuthority('sys:article:save')")
    public ResultVO<?> uploadAvatar(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        String path = obsService.uploadAvatar(multipartFile);
        return ResultVO.ok(path);
    }

    @ResponseBody
    @DeleteMapping("/img/{fileName}")
    @PreAuthorize("hasAuthority('sys:article:del')")
    public ResultVO<?> deletedPostImg(@PathVariable("fileName") String fileName) throws IOException {
         obsService.removeImg(fileName);
        return ResultVO.ok();
    }
    @ResponseBody
    @DeleteMapping("/cover/{fileName}")
    @PreAuthorize("hasAuthority('sys:article:del')")
    public ResultVO<?> deletedPostCover(@PathVariable("fileName") String fileName) throws IOException {
         obsService.removeCover(fileName);
        return ResultVO.ok();
    }
    @ResponseBody
    @PostMapping("/cover")
    @PreAuthorize("hasAuthority('sys:article:save')")
    public ResultVO<?> uploadPostCover(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        String path = obsService.uploadPostCover(multipartFile);
        return ResultVO.ok(path);
    }
}
