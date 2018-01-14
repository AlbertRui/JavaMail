package me.javamail.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author Administrator
 *
 */
public class UUIDUtils {

	/**
	 * 生成随机字符串
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
