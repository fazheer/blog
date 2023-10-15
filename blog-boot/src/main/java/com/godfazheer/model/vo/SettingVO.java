package com.godfazheer.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SettingVO {
    private String avatarUrl;
    private String adminAvatar;
    private String jiaokerAvatar;
    private String projectUrl;
    private String email;
    private String siteName;
    private String typingWord;
    private String name;
    private String personalProfile;
    private Date buildTime;
    private String notice;
    private String icp;
    private String gov;
}
