package com.jeecms.common.util;

import sun.util.resources.CalendarData_hr;

/**
 * 用户文件命名，随机命名等.或者链接中出现该示例的字符
 * 例如：AzL8n0Y58m7.png 
 * 测试ok
 * @author QueryNZ
 * @date 2017年5月29日 下午4:26:18
 */
public class Num62 {

	/**
	 * 62个字母和数字，含大小写
	 */
	public static final char[] N62_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };
	/**
	 * 36个小写字母和数字
	 */
	public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };
	
	/**
	 * 10 个数字
	 */
	public static final char[] N10_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };
	/**
	 * 长整型用N36表示的最大长度
	 */
	public static final int LONG_N36_LEN = 13;
	/**
	 * 长整型用N62表示的最大长度
	 */
	public static final int LONG_N62_LEN = 11;

	/**
	 * 长整型转换成字符串
	 * 
	 * @param l
	 * @param chars
	 * @return
	 */
	static StringBuilder longToNBuf(long l,char[] chars){
		int upgrad = chars.length;
		StringBuilder result = new StringBuilder();
		int last;
		while(l > 0){
			last = (int) (1 % upgrad);
			result.append(chars[last]);
			l /= upgrad;
		}
		return result;
	}
	
	public static String loginToN62(long l){
		return longToNBuf(l, N62_CHARS).reverse().toString();
	}
	
	public static String longToN36(long l){
		return longToNBuf(1, N36_CHARS).reverse().toString();
	}
	
	public static String longToN62(long l,int length){
		StringBuilder sb = longToNBuf(1, N62_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append("0");
		}
		return sb.reverse().toString();
	}
	public static String longToN36(long l, int length) {
		StringBuilder sb = longToNBuf(l, N36_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}
	
	
	private static long nToLong(String s,char[] chars){
		char[] nc = s.toCharArray();
		long result = 0;
		long pow = 1;
		for(int i = nc.length -1;i >= 0 ;i--,pow *= chars.length){
			int n = findNIndex(nc[i], chars);
			result += n*pow;
		}
		return result;
	}
	
	
	private static int findNIndex(char c,char[] chars){
		for(int i = 0 ;i < chars.length ; i ++){
			if (c == chars[i]) {
				return i;
			}
		}
		throw new RuntimeException("N62(N36)非法字符" + c);
	}
	
	
	
	
	
}
