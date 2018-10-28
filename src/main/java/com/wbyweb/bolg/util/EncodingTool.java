package com.wbyweb.bolg.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解决中文乱码问题
 * @author WeiBiaoYi
 *
 */
public class EncodingTool {
	private static Logger logger=LoggerFactory.getLogger(EncodingTool.class);
	/**
	 * 字符串公用方法
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {  
        try {
        	if(StringUtils.isNotEmpty(str)){
        		return new String(str.getBytes("ISO-8859-1"),"UTF-8"); 
        	}else{
        		return null;
        	}
        } catch (Exception e) {  
        	logger.error("字符串转换异常",e);
            return null;  
        }  
    }  
}
