package com.yu.controller;

import com.yu.pojo.UserInformation;
import com.yu.service.UserInformationService;
import com.yu.service.UserPasswordService;
import com.yu.token.TokenProccessor;
import com.yu.tool.SaveSession;
import com.yu.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/16 17:43
 */
@Controller
@Slf4j
public class UserController {
    @Resource
    private UserInformationService userInformationService;
    @Resource
    private UserPasswordService userPasswordService;
    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model){
        String token = TokenProccessor.getInstance().makeToken();
        log.info("进入登录界面，token为:" + token);
        request.getSession().setAttribute("token", token);
        model.addAttribute("token", token);
        return "page/login_page";
    }
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(HttpServletRequest request,@RequestParam(value = "phone") String phone,
                        @RequestParam String password, @RequestParam String token,Model model){
        //从session中获取token
        String loginToken = (String) request.getSession().getAttribute("token");
        if (StringUtils.getInstance().isNullOrEmpty(phone) || StringUtils.getInstance().isNullOrEmpty(password)){
            return "redirect:/login.do";
        }
        //防止重复提交
        if (StringUtils.getInstance().isNullOrEmpty(token) || !token.equals(loginToken)) {
            return "redirect:/login.do";
        }
        boolean b = getId(phone, password, request);
      //失败，不存在该手机号码
        if (!b) {
            model.addAttribute("msg","不存在该手机号码");
            return "redirect:/login.do";
        }
        return "redirect:/";
    }
    private boolean getId(String phone, String password, HttpServletRequest request){
        int uid = userInformationService.selectIdByPhone(phone);
        if (uid == 0 || StringUtils.getInstance().isNullOrEmpty(uid)) {
            return false;
        }
        UserInformation userInformation = userInformationService.selectByPrimaryKey(uid);
        if (null == userInformation) {
            return false;
        }
        password = StringUtils.getInstance().getMD5(password);
        String password2 = userPasswordService.selectByUid(userInformation.getId()).getPassword();
        if (!password.equals(password2)) {
            return false;
        }
        //如果密码账号对应正确，将userInformation存储到session中
        request.getSession().setAttribute("userInformation", userInformation);
        request.getSession().setAttribute("uid", uid);
        SaveSession.getInstance().save(phone,System.currentTimeMillis());
        return true;
    }
}
