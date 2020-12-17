package com.yu.dao;

import com.yu.pojo.UserInformation;

public interface UserInformationMapper {
    int selectIdByPhone(String phone);
    UserInformation selectByPrimaryKey(Integer id);
}
