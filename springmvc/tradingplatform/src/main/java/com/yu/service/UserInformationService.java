package com.yu.service;

import com.yu.pojo.UserInformation;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 10:08
 */
public interface UserInformationService {
    int selectIdByPhone(String phone);//line23
    UserInformation selectByPrimaryKey(Integer id);
}
