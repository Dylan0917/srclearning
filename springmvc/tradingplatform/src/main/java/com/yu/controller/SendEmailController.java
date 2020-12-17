package com.yu.controller;

import com.yu.response.BaseResponse;
import com.yu.service.UserInformationService;
import com.yu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 15:35
 */
@Controller
public class SendEmailController {
    @Resource
    private UserInformationService userInformationService;
    private static final Logger log = LoggerFactory.getLogger(SendEmailController.class);


    @RequestMapping(value = "sendCode.do",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseResponse sendEmail(HttpServletRequest req, HttpServletResponse res,
                                  @RequestParam String phone, @RequestParam String action,
                                  @RequestParam String token){
        res.setContentType("text/html;charset=UTF-8");
        //token，防止重复提交
        String sendCodeToken = (String) req.getSession().getAttribute("token");
        if (StringUtils.getInstance().isNullOrEmpty(sendCodeToken) || !sendCodeToken.equals(token)) {
            return BaseResponse.fail();
        }

    }

}
