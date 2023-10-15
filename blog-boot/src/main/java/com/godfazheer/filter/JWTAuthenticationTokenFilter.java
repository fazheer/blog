package com.godfazheer.filter;

import com.godfazheer.emnus.StatusCode;
import com.godfazheer.model.dto.LoginUserDTO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.RedisServiceImpl;
import com.godfazheer.utils.JacksonUtils;
import com.godfazheer.utils.JwtUtils;
import com.godfazheer.utils.WebUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
每次请求都在请求头携带一个token
所以都会在这里校验一次token
 */
@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisServiceImpl redisService;
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //从请求里面获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)) {
            filterChain.doFilter(request,response);
            return;
        }
        Integer userId;

        try{
            Claims claims = JwtUtils.parseJwtToken(token);
            userId = claims.get("userId",Integer.class);
        }catch (Exception e){
            e.printStackTrace();
            ResultVO<Object> fail = ResultVO.fail(StatusCode.TOKEN_INVAILD.getCode(),StatusCode.TOKEN_INVAILD.getDesc());
            String jsonString = JacksonUtils.toJsonString(fail);
            WebUtils.renderString(response,jsonString);
            return;
        }
        String redisKey = "login:"+userId;
        LoginUserDTO user = redisService.getObejct(redisKey, LoginUserDTO.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
