package com.godfazheer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.model.vo.UserVO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.UserServiceImpl;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/login")
    @PermitAll
    @ResponseBody
    public ResultVO<?> login(@RequestBody UserVO userVO) throws JsonProcessingException {
        Map<String, String> logined = userService.login(userVO);
        return ResultVO.ok(logined,"登陆成功!");
    }
    @GetMapping("/customer/logout")
    @ResponseBody
    @PermitAll
    public ResultVO<?> logout() {
         userService.logout();
        return ResultVO.ok(null,"登出成功!");
    }
}
