package com.yu.dao;

import com.yu.pojo.UserPassword;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 11:39
 */
public interface UserPasswordMapper {
    UserPassword selectByUid(Integer uid);
}
