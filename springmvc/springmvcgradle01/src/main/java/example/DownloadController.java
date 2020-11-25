package example;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/24 17:23
 */
@RequestMapping("/download")
@RestController
public class DownloadController {
    @RequestMapping("/d1")
    public ResultVo<String> downloadFile(@RequestParam("filename") String filename, HttpServletRequest request,HttpServletResponse response){
        try {
            //获取页面输出流
            ServletOutputStream responseOutputStream = response.getOutputStream();
            String realPath = request.getServletContext().getRealPath("/upload");
            //读取文件
            byte[] bytes = FileUtils.readFileToByteArray(new File(realPath + File.separator + filename));
            //向输出流写文件
            //写之前设置响应流以附件的形式打开返回值,这样可以保证前边打开文件出错时异常可以返回给前台
            response.setHeader("Content-Disposition","attachment;filename="+filename);
            responseOutputStream.write(bytes);
            responseOutputStream.flush();
            responseOutputStream.close();
            return ResultVoUtil.success("success");
        } catch (IOException e) {
            e.printStackTrace();
            return ResultVoUtil.error(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());
        }
    }


}
