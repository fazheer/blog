 package com.godfazheer.dao;

 import com.baomidou.mybatisplus.core.mapper.BaseMapper;
 import com.godfazheer.entity.Info;
 import com.godfazheer.model.dto.IcpGovDTO;
 import com.godfazheer.model.dto.InfoDTO;
 import com.godfazheer.model.dto.SettingDTO;
 import com.godfazheer.model.dto.WeekViewDTO;
 import com.godfazheer.model.vo.SettingVO;
 import org.apache.ibatis.annotations.Insert;
 import org.apache.ibatis.annotations.Param;
 import org.apache.ibatis.annotations.Select;
 import org.apache.ibatis.annotations.Update;

 import java.util.List;

 /**
  * <p>
  * Mapper 接口
  * </p>
  *
  * @author M-Y-H
  * @since 2023-05-01
  */
 public interface InfoDao extends BaseMapper<Info> {

  InfoDTO getSiteInfo();
  SettingDTO selectSiteSetting();
  @Insert("insert into sys_view (view) values (#{value})")
  void saveView(@Param("value") Long value);
  @Update("update sys_info set site_view=site_view+#{value}")
  void updateAllView(@Param("value") Long value);
  @Select("select view ,time from sys_view  order by time desc limit 0,7")
  List<WeekViewDTO> selectOneWeekView();

  void updateSetting(@Param("setting") SettingVO settingVO);
  void updateAdminAvatar(@Param("avatarUrl") String avatarUrl);
  void updateJiaokerAvatar(@Param("avatarUrl") String avatarUrl);

  @Select("select icp,gov from sys_info")
  IcpGovDTO selectIcpGov();
 }
