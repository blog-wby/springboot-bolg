package com.wbyweb.bolg.service.impl;

import com.wbyweb.bolg.service.FileService;
import com.wbyweb.bolg.util.FTPUtil;
import com.wbyweb.bolg.util.ImageTools;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private Logger logger=LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file,String path){
        String fileName=file.getOriginalFilename();//获取文件名
        //文件扩展名
        String fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName=UUID.randomUUID().toString().replaceAll("-","")+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传路径:{},新文件名:{}",fileName,path,uploadFileName);
        File fileDir=new File(path);
        if(!fileDir.exists()){
            fileDir.setReadable(true);//设置权限
            fileDir.mkdirs();
        }
        File targetFile=new File(path,uploadFileName);
        try {
            //如果图片宽度大于900则高度不变将宽度变窄
            if(ImageTools.getImgWidth(targetFile)>900){
                Thumbnails.of(file.getInputStream()).size(900,ImageTools.getImgHeight(targetFile)).toFile(targetFile);
                file.transferTo(targetFile);
            }
            if(ImageTools.getFileSize(targetFile)>409600){
                Thumbnails.of(file.getInputStream()).size(ImageTools.getImgWidth(targetFile),ImageTools.getImgHeight(targetFile)).toFile(targetFile);
                file.transferTo(targetFile);
            }
            //文件已经上传成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();
        }catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

    public String uploadThumbnail(MultipartFile file,String path){
        String fileName=file.getOriginalFilename();//获取文件名
        //文件扩展名
        String fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName=UUID.randomUUID().toString().replaceAll("-","")+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传路径:{},新文件名:{}",fileName,path,uploadFileName);
        File fileDir=new File(path);
        if(!fileDir.exists()){
            fileDir.setReadable(true);//设置权限
            fileDir.mkdirs();
        }
        File targetFile=new File(path,uploadFileName);
        try {
            try{
                Thumbnails.of(file.getInputStream()).size(220,150).toFile(targetFile);
            }catch (Exception e){
                file.transferTo(targetFile);
                e.printStackTrace();
            }
            //文件已经上传成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();
        }catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

    @Override
    public boolean removeFile(String srcFname) {
        boolean b=true;
        try {
            b=FTPUtil.removeFile(srcFname);
        } catch (IOException e) {
            b=false;
        }
        return b;
    }
}
