package com.wbyweb.bolg;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Thumbnail {

    @Test
    public void contextLoads() throws Exception {
        //指定大小进行缩放
        //size(宽度, 高度)  
        /*   
         * 若图片横比200小，高比300小，不变   
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变   
         * 若图片横比200大，高比300小，横缩小到200，图片比例不变   
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300   
         */
        Thumbnails.of("C:\\Users\\WeiBiaoYi\\Desktop\\Full Stack\\Java\\lb.jpg").size(220,150).toFile("C:\\Users\\WeiBiaoYi\\Desktop\\Full Stack\\Java\\lb.jpg");
//        Thumbnails.of("images/a380_1280x1024.jpg").size(2560,2048).toFile("c:/a380_2560x2048.jpg");

        //2、按照比例进行缩放
        //scale(比例)  
//        Thumbnails.of("images/a380_1280x1024.jpg").scale(0.25f).toFile("c:/a380_25%.jpg");
//        Thumbnails.of("C:\\Users\\WeiBiaoYi\\Desktop\\Full Stack\\系统图片\\ad.jpg").scale(0.5f).toFile("C:\\Users\\WeiBiaoYi\\Desktop\\Full Stack\\系统图片\\ads.jpg");

        //3、不按照比例，指定大小进行缩放
        //keepAspectRatio(false) 默认是按照比例缩放的  
//        Thumbnails.of("C:\\Users\\WeiBiaoYi\\Desktop\\Full Stack\\系统图片\\ad.jpg").size(200,200).keepAspectRatio(false).toFile("C:\\Users\\WeiBiaoYi\\Desktop\\Full Stack\\系统图片\\add.jpg");

        //4、旋转
        //rotate(角度),正数：顺时针 负数：逆时针  
//        Thumbnails.of("images/a380_1280x1024.jpg").size(1280,1024).rotate(90).toFile("c:/a380_rotate+90.jpg");
//        Thumbnails.of("images/a380_1280x1024.jpg").size(1280,1024).rotate(-90).toFile("c:/a380_rotate-90.jpg");

        //5、水印
        //watermark(位置，水印图，透明度)  
//        Thumbnails.of("images/a380_1280x1024.jpg").size(1280,1024).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File("images/watermark.png")),0.5f).outputQuality(0.8f).toFile("c:/a380_watermark_bottom_right.jpg");
//        Thumbnails.of("images/a380_1280x1024.jpg").size(1280,1024).watermark(Positions.CENTER,ImageIO.read(new File("images/watermark.png")),0.5f).outputQuality(0.8f).toFile("c:/a380_watermark_center.jpg");
        //6、裁剪
        //sourceRegion()  
        //图片中心400*400的区域  
//        Thumbnails.of("images/a380_1280x1024.jpg").sourceRegion(Positions.CENTER,400,400).size(200,200).keepAspectRatio(false).toFile("c:/a380_region_center.jpg");

        //图片右下400*400的区域  
//        Thumbnails.of("images/a380_1280x1024.jpg").sourceRegion(Positions.BOTTOM_RIGHT,400,400).size(200,200).keepAspectRatio(false).toFile("c:/a380_region_bootom_right.jpg");

        //指定坐标  
//        Thumbnails.of("images/a380_1280x1024.jpg").sourceRegion(600,500,400,400).size(200,200).keepAspectRatio(false).toFile("c:/a380_region_coord.jpg");
        //7、转化图像格式
        //outputFormat(图像格式)  
//        Thumbnails.of("images/a380_1280x1024.jpg").size(1280,1024).outputFormat("png").toFile("c:/a380_1280x1024.png");
//        Thumbnails.of("images/a380_1280x1024.jpg").size(1280,1024).outputFormat("gif").toFile("c:/a380_1280x1024.gif");
        //8、输出到OutputStream
        //toOutputStream(流对象)  
//        OutputStream os = new FileOutputStream("c:/a380_1280x1024_OutputStream.png");
//        Thumbnails.of("images/a380_1280x1024.jpg").size(1280,1024).toOutputStream(os);
        //9、输出到BufferedImage
        //asBufferedImage() 返回BufferedImage  
//        BufferedImage thumbnail = Thumbnails.of("images/a380_1280x1024.jpg").size(1280, 1024).asBufferedImage();
//        ImageIO.write(thumbnail,"jpg", new File("c:/a380_1280x1024_BufferedImage.jpg"));
    }
}
