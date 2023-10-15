package com.godfazheer.service;

import com.godfazheer.model.dto.InfoDTO;
import com.godfazheer.model.dto.SettingDTO;
import com.godfazheer.model.dto.WeekViewDTO;

import java.util.List;

public interface InfoService {
    InfoDTO getSiteInfo();

    List<WeekViewDTO> getWeekView();

    Long getAllView();

    SettingDTO getSiteSetting();
}
