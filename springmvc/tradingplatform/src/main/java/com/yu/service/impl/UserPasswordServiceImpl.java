package com.yu.service.impl;

import com.yu.dao.UserPasswordMapper;
import com.yu.pojo.UserPassword;
import com.yu.service.UserPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 11:38
 */
@Service("userPasswordService")
@Slf4j
public class UserPasswordServiceImpl implements UserPasswordService {
    @Resource
    private
    UserPasswordMapper userPasswordMapper;
    @Override
    public UserPassword selectByUid(Integer uid) {
        try {
            return userPasswordMapper.selectByUid(uid);
        } catch (Exception exception) {
            log.error("错误",exception);
            return null;
        }
    }
}
