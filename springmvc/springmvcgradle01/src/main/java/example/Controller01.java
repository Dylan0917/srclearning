package example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
}
