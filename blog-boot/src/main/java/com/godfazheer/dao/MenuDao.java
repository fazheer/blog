package com.godfazheer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfazheer.entity.Menu;
import com.godfazheer.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface MenuDao extends BaseMapper<Menu> {
List<String> selectPermByUserId(@Param("userId") Long userId);
@Select("select menu_name,perm from sys_menu")
List<MenuDTO> selectAllMenus();
}
