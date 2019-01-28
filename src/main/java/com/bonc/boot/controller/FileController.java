package com.bonc.boot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.bonc.boot.service.FileService;
import com.bonc.boot.util.AppReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.util.ClassUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 作用：〈文件上传下载〉
 *
 * @author TanRW
 * @create 2019/1/26
 * @since 1.0.0
 */
@RestController
@RequestMapping("/branch")
@Validated
public class FileController {
    @Resource
    FileService fileService;

    @Value("${rootPath}")
    String rootPath;

    @PostMapping("/fileUpload")
    public AppReply fileUpload(@RequestParam(value = "appendixName", required = false) String appendix_name,
                               @RequestParam(value = "appendixDescribe", required = false) String appendix_describe,
                               @RequestParam(value = "fileName", required = false)@NotEmpty String file_name,
                               @RequestParam(value = "fileUseFor", required = true) String file_use_for,
                               @RequestParam(value = "file", required = true)@NotEmpty MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (!file.isEmpty()) { //获取文件名
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID() + suffixName;
            //指定本地文件夹存储图片
            map.put("file_path", rootPath + fileName);
            try {
                //将图片保存到static文件夹里
                file.transferTo(new File(rootPath + fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        map.put("appendix_name", appendix_name);
        map.put("appendix_describe", appendix_describe);
        map.put("file_name", file_name);
        map.put("file_use_for", file_use_for);
        Object id = fileService.save(map);
        return AppReply.success(id);
    }

    @GetMapping("/fileDownload")
    public AppReply fileDownload(@RequestParam(value = "id", required = true) String id, HttpServletResponse response, HttpServletRequest request) {

        Map<String, String> map = fileService.findById(id);
        if (CollUtil.isEmpty(map)) {
            return AppReply.error("id不存在");
        }
        String file_path = map.get("file_path");
        String file_name = map.get("file_name");
        if (file_path == null || "".equals(file_path)) {
            return AppReply.error("fileUrl不能为空");
        }
//        ServletOutputStream outputStream = null;
//        try {
//            File file = new File(file_path);
//            byte[] bytes = FileUtil.readBytes(file);
//            if (bytes == null || bytes.length == 0) {
//                return AppReply.error("文件不存在");
//            }
//            String fileName = file_path.substring(file_path.lastIndexOf("/") + 1);
//            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            response.setCharacterEncoding("UTF-8");
//            outputStream = response.getOutputStream();
//            outputStream.write(bytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return AppReply.error("下载文件失败");
//        } finally {
//            try {
//                if (outputStream != null) {
//                    outputStream.flush();
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                return AppReply.error("下载文件失败");
//            }
//        }
        ServletContext servletContext = request.getServletContext();
        response.setHeader("Content-type", servletContext.getMimeType(file_name));
        response.setHeader("Content-Disposition", "attachment;filename=" + file_name);
        // 使用io读取文件
        try {
            InputStream in = new FileInputStream(file_path);
            ServletOutputStream out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }





        return AppReply.success();
    }

    @GetMapping("/getFileList")
    public AppReply getFileList(@RequestParam("id") String act_id){
        List<Map<String,Object>> list = fileService.getFileList(act_id);
        return AppReply.success(list);
    }


}