package com.godfazheer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godfazheer.model.vo.UserVO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface UserService {
 Map<String,String> login(UserVO userVO) throws JsonProcessingException;
void logout();

}
