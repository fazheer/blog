package com.godfazheer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InfoDTO {
    private Long articleCount;
    private Long tagCount;
    private Long categoryCount;
    private String avatarUrl;
    private String personalProfile;
    private String projectUrl;
    private String name;
    private String siteName;
    private String typingWord;
    private String notice;
    private Date buildTime;
    private String siteView;
    private Date lastTime;
    private String email;
}
