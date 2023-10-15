package com.godfazheer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_info")
public class Info {
    private String avatarUrl;
    private String personalProfile;
    private String projectUrl;
    private String name;
    private String siteName;
    private String typingWord;
    private String notice;
    private Date buildTime;
    private Long siteView;
    private Date lastTime;
    private String email;
}
