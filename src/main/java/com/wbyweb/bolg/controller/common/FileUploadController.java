package com.wbyweb.bolg.controller.common;

import com.google.common.collect.Maps;
import com.wbyweb.bolg.service.FileService;
import com.wbyweb.bolg.util.FTPUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/file")
@Component
@ConfigurationProperties(prefix = "ftp")
public class FileUploadController {


    @Resource
    public FileService fileService;

    @Value("${ftp.server.http.prefix}")
    public String pprefix;
    /**
     * 图片上传
     * @param session
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public Map<String, Object> upload(
            HttpSession session,
            @RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletRequest request) {

            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.uploadThumbnail(file, path);//上传缩略图
            Map<String, Object> resultMap = Maps.newHashMap();
            resultMap.put("code", 1);
            resultMap.put("msg", FTPUtil.datepath+targetFileName);
            return resultMap;
    }

    /**
     * 富文本上传
     *
     * @param session
     * @param file
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/richtext_img_upload")
    public Map<String, Object> richtextImgUpload(
            @RequestParam(value = "editormd-image-file", required = false) MultipartFile file,
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> resultMap = Maps.newHashMap();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file, path);//原图上传
        if (StringUtils.isBlank(targetFileName)) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            return resultMap;
        }
        String url = pprefix+FTPUtil.datepath+targetFileName;
        resultMap.put("success", 1);
        resultMap.put("message", "上传成功！");
        resultMap.put("url",url);
        return resultMap;
    }
}
