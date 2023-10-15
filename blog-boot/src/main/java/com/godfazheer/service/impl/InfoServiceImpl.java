package com.godfazheer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.godfazheer.dao.InfoDao;
import com.godfazheer.entity.Info;
import com.godfazheer.model.dto.IcpGovDTO;
import com.godfazheer.model.dto.InfoDTO;
import com.godfazheer.model.dto.SettingDTO;
import com.godfazheer.model.dto.WeekViewDTO;
import com.godfazheer.model.vo.SettingVO;
import com.godfazheer.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoDao infoDao;
    @Override
    public InfoDTO getSiteInfo() {
        return infoDao.getSiteInfo();
    }
    @Override
    public List<WeekViewDTO> getWeekView() {
        return infoDao.selectOneWeekView();
    }
    @Override
    public Long getAllView() {
        LambdaQueryWrapper<Info> lqw = new LambdaQueryWrapper<Info>().select(Info::getSiteView);
        return infoDao.selectOne(lqw).getSiteView();
    }

    @Override
    public SettingDTO getSiteSetting() {
        return infoDao.selectSiteSetting();
    }
    public void updateSiteSetting(SettingVO settingVO) {
        infoDao.updateAdminAvatar(settingVO.getAdminAvatar());
        infoDao.updateJiaokerAvatar(settingVO.getJiaokerAvatar());
        infoDao.updateSetting(settingVO);
    }

    public IcpGovDTO getIcpAndGov() {
        return infoDao.selectIcpGov();
    }
}
