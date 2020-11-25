package example;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/11/24 9:40
 */
@Controller
@RequestMapping("/c03")
public class Controller03 {
    @RequestMapping("m01")
//    @PostMapping
    public String fileUpload(@RequestParam("photo")CommonsMultipartFile file, HttpServletRequest servletRequest, Model model) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)){
            return "redirect:index.jsp";
        }
        String realPath = servletRequest.getServletContext().getRealPath("/upload");
        File file1 = new File(realPath);
        if (!file1.exists()){
            file1.mkdir();
        }
        System.out.println("上传文件保存地址：" + file1.getAbsolutePath());
        InputStream in = file.getInputStream();
        OutputStream outputStream = new FileOutputStream(new File(file1,originalFilename));
        //读取写出
        int len=0;
        byte[] buffer = new byte[1024];
        while ((len=in.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
            outputStream.flush();
        }
        in.close();
        outputStream.close();
        model.addAttribute("filename",originalFilename);
        return "fileSuccess1";

    }
    @RequestMapping(value = "m02",method = RequestMethod.POST)
    public String multiFileUpload(@RequestParam("file")CommonsMultipartFile[] files, HttpServletRequest servletRequest, Model model) throws IOException {
        String realPath = servletRequest.getServletContext().getRealPath("/upload");
        File file1 = new File(realPath);
        if (!file1.exists()){
            file1.mkdir();
        }
        System.out.println("上传文件保存地址：" + file1.getAbsolutePath());
        //用来存放上传后的路径地址的变量
        List<File> uploadFiles = new ArrayList<File>();
        for(int i = 0; i < files.length; i++)
        {
            CommonsMultipartFile multipartFile = files[i];
            String originalFilename = multipartFile.getOriginalFilename();
            if (!StringUtils.isEmpty(originalFilename)){
                //获取文件字节流
                byte[] bytes = multipartFile.getBytes();
                //新文件路径
                File serverFile = new File( realPath + File.separator + originalFilename );
                //将文件字节流输出到刚创建的文件上
                BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream( serverFile ) );
                stream.write(bytes);
                stream.close();
            }
        }
        return "success";
    }
}
