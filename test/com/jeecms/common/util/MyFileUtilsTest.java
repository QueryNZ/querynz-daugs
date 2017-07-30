package com.jeecms.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jeecms.common.util.Zipper.FileEntry;

public class MyFileUtilsTest {

	
	/**
	 * 像这种测试，以后要走到控制台打印目录全部的文件信息
	 * 2017年5月29日 下午4:18:23
	 * ps:
	 * @param
	 */
	@Test
	public void testiterateFolder() throws IOException {
		MyFileUtils myFileUtils = new MyFileUtils();
		((MyFileUtils) myFileUtils).getFiles(new File("F:/"));
	}
}
