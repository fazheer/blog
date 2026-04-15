package com.godfazheer.controller;

import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.ObsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("file")
public class FileController {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final Set<String> ALLOWED_EXTENSIONS = new HashSet<>(Arrays.asList("jpg", "jpeg", "png", "gif", "webp"));

    @Autowired
    private ObsServiceImpl obsService;

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上传文件不能为空");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("上传文件大小不能超过 5MB");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String ext = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(ext)) {
                throw new IllegalArgumentException("不支持的文件类型，仅允许: " + ALLOWED_EXTENSIONS);
            }
        }
    }

    @PostMapping("/img")
    @PreAuthorize("hasAuthority('sys:article:save')")
    public ResultVO<?> uploadPostImg(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        validateFile(multipartFile);
        String path = obsService.uploadPostImg(multipartFile);
        return ResultVO.ok(path);
    }

    @PostMapping("/avatar")
    @PreAuthorize("hasAuthority('sys:article:save')")
    public ResultVO<?> uploadAvatar(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        validateFile(multipartFile);
        String path = obsService.uploadAvatar(multipartFile);
        return ResultVO.ok(path);
    }

    @DeleteMapping("/img/{fileName}")
    @PreAuthorize("hasAuthority('sys:article:del')")
    public ResultVO<?> deletedPostImg(@PathVariable("fileName") String fileName) throws IOException {
        obsService.removeImg(fileName);
        return ResultVO.ok();
    }

    @DeleteMapping("/cover/{fileName}")
    @PreAuthorize("hasAuthority('sys:article:del')")
    public ResultVO<?> deletedPostCover(@PathVariable("fileName") String fileName) throws IOException {
        obsService.removeCover(fileName);
        return ResultVO.ok();
    }

    @PostMapping("/cover")
    @PreAuthorize("hasAuthority('sys:article:save')")
    public ResultVO<?> uploadPostCover(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        validateFile(multipartFile);
        String path = obsService.uploadPostCover(multipartFile);
        return ResultVO.ok(path);
    }
}
