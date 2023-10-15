package com.godfazheer.handler;

import com.godfazheer.emnus.StatusCode;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.utils.JacksonUtils;
import com.godfazheer.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResultVO<Object> fail = ResultVO.fail(StatusCode.AUTHORIZED.getCode(),StatusCode.AUTHORIZED.getDesc());
        String jsonString = JacksonUtils.toJsonString(fail);
        WebUtils.renderString(response,jsonString);
    }
}
