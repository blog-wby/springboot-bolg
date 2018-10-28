package com.wbyweb.bolg.util;

import java.util.UUID;

/**
 * 获取uuid主键
 * @author biaoyi
 *
 */
public class UuidUtil {

	
	/**
	 * 获取32位uuid
	 * @return
	 */
	
	public static String getUUID(){ 
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", ""); 
		//System.out.println(uuid);
		return uuid; 
	}
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
	
}
