package com.wbyweb.bolg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * FTP服务器
 * 
 * @author WeiBiaoYi
 */
@Component
public class FTPUtil {

	private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIP;
    private static String ftpUser;
    private static String ftpPassword;
    public static String filepath;

    @Value("${ftp.server.ip}")
    public void setFtpIp(String ftpserverip){
        ftpIP=ftpserverip;
    }
    @Value("${ftp.username}")
    public void setFtpUser(String ftpusername){
        ftpUser=ftpusername;
    }
    @Value("${ftp.passwd}")
    public void setFtpPassword(String ftppasswd){
        ftpPassword=ftppasswd;
    }
    @Value("${ftp.filepath}")
    public void setFilepath(String ftpfilepath){
        filepath=ftpfilepath;
    }


	private String ip;
	private int port;
	private String user;
	private String pwd;
	private FTPClient ftpClient;

	public static String datepath="";


	public FTPUtil() {

	}

	public FTPUtil(String ip, int port, String user, String pwd) {
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.pwd = pwd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}

	public static boolean uploadFile(List<File> fileList) throws IOException {
		FTPUtil ftpUtil = new FTPUtil(ftpIP, 21, ftpUser, ftpPassword);
		logger.info("开始连接ftp服务器");
		boolean result = ftpUtil.uploadFile(filepath, fileList);
		logger.info("开始连接ftp服务器,结束上传,上传结果:{}", result);
		return result;
	}

	public boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
		boolean uploaded = false;
		FileInputStream fis = null;
		// 链接FIP服务器
		if (connectServer(this.ip, this.port, this.user, this.pwd)) {
			try {
                logger.info("文件路径："+remotePath);
				boolean changeWorkingDirectory = ftpClient.changeWorkingDirectory(remotePath);
				logger.info("文件路径是否存在："+changeWorkingDirectory+"--------------");
				String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String[] split = format.split("-");
				StringBuilder sb=new StringBuilder("/");
				for (String string : split) {
					if(!ftpClient.changeWorkingDirectory(string)){
						ftpClient.changeWorkingDirectory(sb.toString());
						ftpClient.makeDirectory(string);
					}
					sb.append(string+"/");
				}
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("UTF-8");
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 二级制
				ftpClient.enterLocalPassiveMode();
				ftpClient.setDataTimeout(60000);			//设置传输超时时间为60秒 
				ftpClient.setConnectTimeout(60000);			//连接超时为60秒
				for (File fileitem : fileList) {
					fis = new FileInputStream(fileitem);
					ftpClient.changeWorkingDirectory(filepath+sb.toString());
                    datepath=sb.toString();
					ftpClient.storeFile(fileitem.getName(),fis);
				}
                uploaded = true;
			} catch (IOException e) {
				uploaded = false;
				logger.error("上传文件异常", e);
			} finally {
				fis.close();
				ftpClient.disconnect();
			}
		}
		return uploaded;
	}

	/**
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static boolean removeFile(String fileName) throws IOException {
		FTPUtil ftpUtil = new FTPUtil(ftpIP, 21, ftpUser, ftpPassword);
		logger.info("开始连接ftp服务器");
		boolean result = ftpUtil.removeFile("img", fileName);
		logger.info("开始连接ftp服务器,结束上传,上传结果:{}", result);
		return result;
	}

	public boolean removeFile(String remotePath, String fileName) throws IOException {
		boolean flag = true;
		// 链接FIP服务器
		if (connectServer(this.ip, this.port, this.user, this.pwd)) {
			if (ftpClient != null) {
				try {
					flag = ftpClient.deleteFile(new String(fileName.getBytes("GBK"), "iso-8859-1")); 
					logger.info("开始连接ftp服务器,删除文件,删除结果:{}",flag);
				} catch (IOException e) {
					logger.info("开始连接ftp服务器,删除文件,删除结果:{}", e.getMessage());
					flag = false;
				}
			}
		}
		return flag;
	}

	private boolean connectServer(String ip, int port, String user, String pwd) {
		ftpClient = new FTPClient();
		boolean isSuccess = false;
		try {
			ftpClient.connect(ip);
			isSuccess = ftpClient.login(user, pwd);
			return isSuccess;
		} catch (IOException e) {
			logger.error("链接FTP服务器异常", e);
		}
		return isSuccess;
	}
    
}
