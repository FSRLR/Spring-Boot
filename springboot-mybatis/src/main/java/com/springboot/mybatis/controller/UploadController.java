package com.springboot.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 上传文件控制器
 * 直接上传到服务器
 * @author 26
 * 2019.3.25
 */

@RestController
public class UploadController {
    //指定一个临时路径作为上传目录
/*    private static String UPLOAD_FOLDER="E:/temp/";*/

    //遇到http://localhost:8080,则跳转到upload.html页面
    @GetMapping("/")
    public String index(){
        return "upload";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file")MultipartFile srcFile,
                             RedirectAttributes redirectAttributes){
         String returnFileName="";
        //选择了文件，开始进行上传操作
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmmss");
        String date=sf.format(new Date());
        try {
            //构建上传目标路径
            File destFile=new File(ResourceUtils.getURL("classpath:").getPath());
            if (!destFile.exists()){
                destFile=new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:"+destFile.getAbsolutePath());
            //拼接static目录
            File upload=new File(destFile.getAbsolutePath(),"upload/"+date);
            //若目标文件夹不存在，则创建一个
            if (!upload.exists()){
                upload.mkdirs();
            }
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // 获得文件原始名称
            String fileName = srcFile.getOriginalFilename();
            // 获得文件后缀名称
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            // 生成最新的uuid文件名称
            String newFileName = uuid + "."+ suffixName;
            System.out.println("完整的上传路径:"+upload.getAbsolutePath()+"/"+srcFile.getOriginalFilename());
            //根据srcFile的大小，准备一个字节数组
            byte[] bytes=srcFile.getBytes();
            //通过项目路径，拼接上传路径
            Path path=Paths.get(upload.getAbsolutePath()+"/"+newFileName);
            //最重要的一步，将源文件写入目标地址
            Files.write(path,bytes);
            returnFileName="http://localhost:8080/upload/"+newFileName;
            //将文件上传成功的信息写入messages
            redirectAttributes.addFlashAttribute("message", "文件上传成功！"+newFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }

}
