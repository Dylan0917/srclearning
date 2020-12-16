package com.yu.controller;

import com.yu.pojo.UserInformation;
import com.yu.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/15 19:29
 */
@Controller
@Slf4j
public class HomeController {
    @RequestMapping(value = {"/","home.do"})
    public String home(HttpServletRequest request, Model model){
        log.info("com.yu.controller.HomeController.home");
        UserInformation userInformation = (UserInformation) request.getSession().getAttribute("userInformation");
        if (!StringUtils.getInstance().isNullOrEmpty(userInformation)){
            model.addAttribute("userInformation",userInformation);
        }else {
            userInformation = new UserInformation();
            model.addAttribute("userInformation", userInformation);
        }
        return "index";
    }

}
