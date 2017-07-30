package com.jeecms.common.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class Num62Test {

	// 最大值就是全是1 N36_CHARS  最高为11位 除了数字以外+一个字符的随机
	@Test
	public void testlongToNBuf() throws IOException {
		Num62 num62 = new Num62();

		System.out.println(num62.longToNBuf(Long.MAX_VALUE,num62.N36_CHARS).reverse().toString());
		
	}
	
	@Test
	public void testlongToN62() throws IOException {
		Num62 num62 = new Num62();

		System.out.println(num62.longToN62(Long.MAX_VALUE,3));
		
	}
	
	@Test
	public void testfindNIndex() throws IOException {
		Num62 num62 = new Num62();

//		System.out.println(num62.findNIndex('d',num62.N36_CHARS));
		
	}
	
	@Test
	public void testnToLong() throws IOException {
		Num62 num62 = new Num62();

//		System.out.println(num62.nToLong("d",num62.N36_CHARS));
		
	}
}
