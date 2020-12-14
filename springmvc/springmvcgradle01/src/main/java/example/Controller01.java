package example;

import example.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/10 10:45
 */
@Controller
@RequestMapping(value = "/c01")
public class Controller01 {
//    @RequestMapping({"m01","m011"})
    @RequestMapping(value = "m01")
    public String m01(HttpServletRequest request){
       // ServletContext servletContext = request.getServletContext();
       // WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        /*No qualifying bean of type 'org.springframework.web.servlet.view.InternalResourceViewResolver' available*/
       // Object o = webApplicationContext.getBean(InternalResourceViewResolver.class);
         ServletContext servletContext = request.getServletContext();
         WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext, "org.springframework.web.servlet.FrameworkServlet.CONTEXT.DispatcherServlet");
         // 成功
         Object o = webApplicationContext.getBean(InternalResourceViewResolver.class);
        return "success";
    }
    @RequestMapping("m02")
    @ResponseBody
    public Map<String,String> m01(){
        Map<String,String> ret = new HashMap<String,String>();
        ret.put("name","json");
        return ret;
    }
    @RequestMapping(value = "m?03") /*?可以匹配任意一个字符*/
    public String m03(){
        return "success1";
    }
    @RequestMapping(value = "m*03") /* *可以匹配任意多个字符*/
    public String m04(){
        return "success2";
    }
    @RequestMapping(value = "m/**/03") /* **可以匹配任意多层*/
    public String m05(){
        return "success3";
    }
    /*@PathVariable 为REST请求提供支持*/
    @RequestMapping(value = "m06/{id}",method = RequestMethod.GET)
    public String m06(@PathVariable String id){
        System.out.println("get请求：" + id);
        return "success4";
    }
    /*@PathVariable 为REST请求提供支持*/
    @RequestMapping(value = "m06/{id}",method = RequestMethod.POST)
    public String m07(@PathVariable String id){
        System.out.println("POST请求：" + id);
        return "success4";
    }
    /*@RequestParam 获取请求参数 使用Model向页面传递数据*/
    @RequestMapping(value = "m08",method = RequestMethod.POST)
    public String m08(@RequestParam String id, @RequestHeader(value = "Cookie") String cookie01,Model model){
        model.addAttribute("msg",id);
        model.addAttribute("cookie01",cookie01);
        System.out.println("POST请求：" + id);
        System.out.println("POST请求Cookie：" + cookie01);
//        POST请求：12
//        POST请求Cookie：JSESSIONID=74FE344A450F95B045796525CEF863AC
        return "success5";
    }
    /*SpringMVC中使用原生API 使用request向页面传递数据*/
    @RequestMapping(value = "m09",method = RequestMethod.POST)
    public String m09(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String id = request.getParameter("id");
        Long lon = session.getCreationTime();
        //返回到页面
        request.setAttribute("id",id);
        request.setAttribute("sessionCreateTime",lon.toString());
        System.out.println("POST请求：" + id);
        return "success6";
    }
    /*SpringMVC中使用原生API 数组*/
    @RequestMapping(value = "m10",method = RequestMethod.POST)
    public String m10(@RequestParam("hobbies") String[] ho){
        System.out.println(Arrays.toString(ho));;
        return "success";
    }
    /*SpringMVC中使用原生API pojo*/
    @RequestMapping(value = "m11",method = RequestMethod.POST)
    public String m11(User user){
        /*User{uid='23', userName='分时段'}*/
        System.out.println(user.toString());
        return "success";
    }
    /*SpringMVC中使用原生API 使用map向页面传递数据*/
    @RequestMapping(value = "m12",method = RequestMethod.POST)
    public String m12(Map<String,Object> map){
        map.put("des","使用map向页面传递数据(从Controller返回)");
        return "success7";
    }
    /*SpringMVC中使用原生API 使用map向页面传递数据*/
    @RequestMapping(value = "m13",method = RequestMethod.POST)
    public ModelAndView m13(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success7");
        mv.addObject("des1","使用ModelAndView向页面传递数据(从Controller返回des1)");
       return mv;
    }
    /*spring全局类型转换器*/
    @RequestMapping(value = "m14",method = RequestMethod.POST)
    public ModelAndView m14(@RequestParam("inDate") Date indate){
        System.out.println(indate);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success7");
       return mv;
    }

    /*spring局部异常处理*/
//    当出了异常之后，会在当前controller类中找继承关系最浅的那个异常处理方法
    @RequestMapping(value = "m15")
    public String m15() {
//        该异常会由handleException02处理
        int a = 10 / 0;
        System.out.println(a);
        return "success";
    }
  /*  @ExceptionHandler(value = {Exception.class})
    public ModelAndView handleException01(Exception e){
        e.printStackTrace();
        System.out.println("handleException01.................");
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMsg",e.getLocalizedMessage());
        return mv;
    }
    @ExceptionHandler(value = {RuntimeException.class})
    public ModelAndView handleException02(Exception e){
        e.printStackTrace();
        System.out.println("handleException02.................");
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMsg",e.getLocalizedMessage());
        return mv;
    }*/
}
