package com.godfazheer.service.impl;

import com.godfazheer.dao.MenuDao;
import com.godfazheer.entity.Menu;
import com.godfazheer.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuDao;
    public  int saveMenu(Menu menu){
        return menuDao.insert(menu);
    }
}
