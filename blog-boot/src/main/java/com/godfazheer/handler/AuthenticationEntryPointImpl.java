package com.godfazheer.handler;

import com.godfazheer.emnus.StatusCode;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.utils.JacksonUtils;
import com.godfazheer.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ResultVO<Object> fail = ResultVO.fail(StatusCode.LOGIN_FAILED.getCode(),StatusCode.LOGIN_FAILED.getDesc());
        String jsonString = JacksonUtils.toJsonString(fail);
        WebUtils.renderString(response,jsonString);
    }
}
