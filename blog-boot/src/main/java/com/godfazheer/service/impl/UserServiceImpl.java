package com.godfazheer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.model.dto.LoginUserDTO;
import com.godfazheer.model.vo.UserVO;
import com.godfazheer.service.UserService;
import com.godfazheer.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private RedisServiceImpl redisService;
    @Override
    public Map<String,String> login(UserVO userVO) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userVO.getUsername(),userVO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUserDTO loginUserDTO = (LoginUserDTO) authenticate.getPrincipal();
        Long userId=loginUserDTO.getId();
        String username=loginUserDTO.getUsername();
        redisService.setObject("login:"+userId,loginUserDTO);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("username",username);
        HashMap<String, String> result = new HashMap<>();
        result.put("token",JwtUtils.generateJwtToken(map));
        result.put("avatarUrl",loginUserDTO.getAvatar());
        return result;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDTO loginUserDTO = (LoginUserDTO) authentication.getPrincipal();
        Long userId= loginUserDTO.getId();
        redisService.delKey("login:"+userId);
    }
}
