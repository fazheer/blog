package com.godfazheer.handler;

import com.godfazheer.enums.StatusCode;
import com.godfazheer.model.vo.result.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResultVO<?> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("权限不足: {}", e.getMessage());
        return ResultVO.fail(StatusCode.AUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResultVO<?> handleAuthenticationException(AuthenticationException e) {
        log.warn("认证失败: {}", e.getMessage());
        return ResultVO.fail(StatusCode.NOT_LOGIN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("参数错误: {}", e.getMessage());
        return ResultVO.fail(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultVO<?> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常: {}", e.getMessage(), e);
        return ResultVO.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<?> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return ResultVO.fail(StatusCode.SYSTEM_ERROR);
    }
}
