package com.godfazheer.controller;

import com.godfazheer.model.dto.InfoDTO;
import com.godfazheer.model.dto.WeekViewDTO;
import com.godfazheer.model.vo.SettingVO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.InfoServiceImpl;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class SiteInfoController {
    @Autowired
    InfoServiceImpl infoService;
    @GetMapping
    @ResponseBody
    @PermitAll
    public ResultVO<?> getInfo() {
        InfoDTO siteInfo = infoService.getSiteInfo();
        return ResultVO.ok(siteInfo);
    }
    @GetMapping("/week")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getOneWeekViewI() {
        List<WeekViewDTO> weekView = infoService.getWeekView();
        return ResultVO.ok(weekView);
    }
    @GetMapping("/view")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getAllView() {

        return ResultVO.ok(infoService.getAllView());
    }
     @GetMapping("/setting")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getSiteSetting() {
        return ResultVO.ok(infoService.getSiteSetting());
    }
    @PutMapping("/setting")
    @ResponseBody
    @PermitAll
    public ResultVO<?> updateSiteSetting(@RequestBody SettingVO settingVO) {
        infoService.updateSiteSetting(settingVO);
        return ResultVO.ok();
    }
    @GetMapping("/icp")
    @ResponseBody
    @PermitAll
    public ResultVO<?> getSiteIcpAndGov(){
        return ResultVO.ok(infoService.getIcpAndGov());
    }

}
