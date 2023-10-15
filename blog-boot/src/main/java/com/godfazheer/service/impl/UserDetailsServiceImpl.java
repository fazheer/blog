package com.godfazheer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.godfazheer.dao.MenuDao;
import com.godfazheer.dao.UserDao;
import com.godfazheer.entity.User;
import com.godfazheer.model.dto.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDao menuDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, username);
        User user = userDao.selectOne(lqw);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名错误");
        }
        List<String> perms = menuDao.selectPermByUserId(user.getId());
        return LoginUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .avatar(user.getAvatarUrl())
                .permissions(perms)
                .build();
    }
}
