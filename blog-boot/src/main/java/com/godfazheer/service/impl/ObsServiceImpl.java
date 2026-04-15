package com.godfazheer.service.impl;

import com.obs.services.ObsClient;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class ObsServiceImpl {
    @Value("${obs.bucket-name:myh-blog-bucket}")
    private String bucketName;
    @Value("${obs.endpoint:https://obs.cn-north-1.myhuaweicloud.com}")
    private String endPoint;
    @Value("${obs.ak:}")
    private String ak;
    @Value("${obs.sk:}")
    private String sk;
    @Value("${obs.domain:https://obs.jiaoker.cn}")
    private String obsDomain;

    private ObsClient obsClient;

    @PostConstruct
    public void init() {
        if (ak == null || ak.isBlank() || sk == null || sk.isBlank()) {
            log.warn("OBS AK/SK 未配置，文件上传功能将不可用");
            return;
        }
        this.obsClient = new ObsClient(ak, sk, endPoint);
    }

    @PreDestroy
    public void destroy() {
        if (this.obsClient != null) {
            try {
                this.obsClient.close();
            } catch (IOException e) {
                log.error("关闭 ObsClient 失败", e);
            }
        }
    }

    private void ensureClient() {
        if (obsClient == null) {
            throw new IllegalStateException("OBS 客户端未初始化，请检查 AK/SK 配置");
        }
    }

    private String generateUuidFileName(String originalFilename) {
        if (originalFilename == null || originalFilename.isBlank()) {
            return UUID.randomUUID().toString();
        }
        int dotIndex = originalFilename.lastIndexOf('.');
        String ext = dotIndex > 0 ? originalFilename.substring(dotIndex) : "";
        return UUID.randomUUID() + ext;
    }

    public String uploadPostImg(MultipartFile multipartFile) throws IOException {
        ensureClient();
        String fileName = generateUuidFileName(multipartFile.getOriginalFilename());
        obsClient.putObject(bucketName, "post_img/" + fileName, multipartFile.getInputStream());
        return obsDomain + "/post_img/" + fileName;
    }

    public String uploadAvatar(MultipartFile multipartFile) throws IOException {
        ensureClient();
        String fileName = generateUuidFileName(multipartFile.getOriginalFilename());
        obsClient.putObject(bucketName, "avatar/" + fileName, multipartFile.getInputStream());
        return obsDomain + "/avatar/" + fileName;
    }

    public String uploadPostCover(MultipartFile multipartFile) throws IOException {
        ensureClient();
        String fileName = generateUuidFileName(multipartFile.getOriginalFilename());
        obsClient.putObject(bucketName, "post_cover/" + fileName, multipartFile.getInputStream());
        return obsDomain + "/post_cover/" + fileName;
    }

    public void removeImg(String fileName) throws IOException {
        ensureClient();
        obsClient.deleteObject(bucketName, "post_img/" + fileName);
    }

    public void removeCover(String fileName) throws IOException {
        ensureClient();
        obsClient.deleteObject(bucketName, "post_cover/" + fileName);
    }
}
