package com.jeecms.common.office;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class FileUtilsTest {

	FileUtils fileUtils = new FileUtils();
	
	@Test
	public void testgetFilePrefix() {
		
		System.out.println(fileUtils.getFilePrefix("测试.html.txt"));
	}
	
	@Test
	public void testgetFileSufix() {
		
		System.out.println(fileUtils.getFileSufix("测试.html.txt"));
	}
	
	@Test
	public void testgetFileName() {
		
		System.out.println(fileUtils.getFileName("c:/测试/测试.html.txt"));
	}
	
	
	@Test
	public void testgetFilePath() {
		
		System.out.println(fileUtils.getFilePath("c:/测试/测试.html/1.txt"));
	}
	
	@Test
	public void testlistFiles() {
		
		System.out.println(fileUtils.listFiles(new File("f:/"),"2017-05","png"));
	}
	
	@Test
	public void testcopyFile() throws FileNotFoundException {
		
		fileUtils.copyFile("f:/答辩申请.docx","f:/1.docx");
	}

}
