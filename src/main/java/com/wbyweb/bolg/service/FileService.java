package com.wbyweb.bolg.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * WeiBiaoYi
 */
public interface FileService {
	/**
	 * 文件上传
	 * @param file
	 * @param path
	 * @return
	 */
	String upload(MultipartFile file, String path);

    /**
     * 上传缩略图
     * @param file
     * @param path
     * @return
     */
    String uploadThumbnail(MultipartFile file, String path);
	/**
	 * 文件删除
	 * @param srcFname
	 * @return
	 */
	boolean removeFile(String srcFname);
}
