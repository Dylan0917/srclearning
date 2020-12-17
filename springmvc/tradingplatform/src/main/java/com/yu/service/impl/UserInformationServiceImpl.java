package com.yu.service.impl;

import com.yu.dao.UserInformationMapper;
import com.yu.pojo.UserInformation;
import com.yu.service.UserInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 10:08
 */
@Service("userInformationService")
@Slf4j
public class UserInformationServiceImpl implements UserInformationService {
    @Resource
    private UserInformationMapper userInformationMapper;
    @Override
    public int selectIdByPhone(String phone) {
        try {
            return userInformationMapper.selectIdByPhone(phone);
        } catch (Exception exception) {
            log.error("失败",exception);
            return 0;
        }
    }

    @Override
    public UserInformation selectByPrimaryKey(Integer id) {
        try {
            return userInformationMapper.selectByPrimaryKey(id);
        } catch (Exception exception) {
            log.error("失败",exception);
            return null;
        }
    }
}
