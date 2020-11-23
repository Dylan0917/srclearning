package example;

import example.bean.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/12 11:36
 */
@RequestMapping("/c02")
@RestController
public class Controller02 {
    /*
    问题 org.springframework.web.HttpMediaTypeNotSupportedException: Content type 'application/json;charset=UTF-8' not supported 去掉@RequestParam
    *问题 [org.springframework.http.converter.HttpMessageNotWritableException: No converter found for return value of type: class java.util.HashMap] 缺少jar包
    @RequestBody必须加
    * */
    @RequestMapping(value = "/m01",method = RequestMethod.POST)
    @ResponseBody
    public Map m01(@RequestBody @Valid User user, BindingResult br){
        br.getAllErrors();
        HashMap ret = new HashMap();
        ret.put("name",user.getUserName());
        return ret;
    }
}
