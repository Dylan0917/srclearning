package example;

import example.bean.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/m01")
    public Map m01(@RequestBody User user){
        Map ret = new HashMap();
        ret.put("name",user.getUserName());
        return ret;
    }
}
