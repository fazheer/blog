package com.godfazheer.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.dao.InfoDao;
import com.godfazheer.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SaveSiteViewHandler {
    @Autowired
    private RedisServiceImpl redisService;
    @Autowired
    private InfoDao infoDao;
    @Scheduled(cron = "00 55 23 * * ?")
    public void saveSiteView() throws JsonProcessingException {
        Long view = redisService.getObejct("sys:view", Long.class);
        infoDao.updateAllView(view);
        infoDao.saveView(view);
        redisService.setObject("sys:view", 0L);
    }
}
