package com.wbyweb.bolg.util;

import java.security.MessageDigest;

public class MD5util {

	// 创建MD5加密的方法(加密字符串)
	public static String GetMD5String(String pwd) {
		String md5Result = "";
		try {
			// 生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要。
			md.update(pwd.getBytes());
			// 通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			// 生成具体的md5密码到buf数组
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位的加密
			System.out.println("32位加密: " + buf.toString());
			md5Result = buf.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Result.toUpperCase();

	}
	public static void main(String[] args) {
		GetMD5String("admin");
	}
}
