package com.yu.service;

import com.yu.pojo.UserPassword;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 11:37
 */
public interface UserPasswordService {
    UserPassword selectByUid(Integer uid);
}
