package example;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yu.wenhua
 * @desc  * @param <S> the source type
 *  * @param <T> the target type
 *  符合源为String 目标为Date的时候才会调用转换器
 * @date 2020/11/23 11:02
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source == null){
            throw new RuntimeException("请输入值");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("转换失败");
        }
    }
}
