package com.godfazheer.service.impl;

import com.obs.services.ObsClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ObsServiceImpl {
    private final String bucketName = "myh-blog-bucket";
    private final String endPoint = "https://obs.cn-north-1.myhuaweicloud.com";
    private final String ak = "QN5SCCNRWX4SOJFQLBJ3";
    private final String sk = "OV13QdQfWOToL6Q8Q0j66BScS9Ck1IjDB58XiBDd";

    public String uploadPostImg(MultipartFile multipartFile) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        String fileName = multipartFile.getOriginalFilename();
        obsClient.putObject(bucketName, "post_img/" + String.valueOf(System.currentTimeMillis()), multipartFile.getInputStream());
        obsClient.close();
        return "https://obs.jiaoker.cn/post_img/" + fileName;
    }
    public String uploadAvatar(MultipartFile multipartFile) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        String fileName = multipartFile.getOriginalFilename();
        obsClient.putObject(bucketName, "avatar/" + fileName, multipartFile.getInputStream());
        obsClient.close();
        return "https://obs.jiaoker.cn/avatar/" + fileName;
    }

    public String uploadPostCover(MultipartFile multipartFile) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        obsClient.putObject(bucketName, "post_cover/" + fileName, multipartFile.getInputStream());
        obsClient.close();
        return "https://obs.jiaoker.cn/post_cover/" + fileName;
    }

    public void removeImg(String fileName) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        obsClient.deleteObject(bucketName, "post_img/"+fileName);
        obsClient.close();
    }
    public void removeCover(String fileName) throws IOException {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        obsClient.deleteObject(bucketName, "post_cover/" + fileName);
        obsClient.close();
    }

}
