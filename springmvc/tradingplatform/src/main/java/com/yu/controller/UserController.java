package com.yu.controller;

import com.yu.token.TokenProccessor;
import com.yu.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/16 17:43
 */
@Controller
@Slf4j
public class UserController {
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
                        @RequestParam String password, @RequestParam String token){
        //从session中获取token
        String loginToken = (String) request.getSession().getAttribute("token");
        if (StringUtils.getInstance().isNullOrEmpty(phone) || StringUtils.getInstance().isNullOrEmpty(password)){
            return "redirect:/login.do";
        }
        //防止重复提交
        if (StringUtils.getInstance().isNullOrEmpty(token) || !token.equals(loginToken)) {
            return "redirect:/login.do";
        }


    }
}
