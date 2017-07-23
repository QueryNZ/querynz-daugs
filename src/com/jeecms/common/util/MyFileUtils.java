package com.jeecms.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试ok
 * @author QueryNZ
 * @date 2017年5月29日 下午6:36:42
 */
public class MyFileUtils {

	public static List<File> getFiles(File folder){
		List<File>files=new ArrayList<File>();
		iterateFolder(folder, files);
		return files;
	}
	
	private static void iterateFolder(File folder,List<File> files){
		File flist[] =  folder.listFiles();
		files.add(folder);
		if (flist == null || flist.length == 0) {
			files.add(folder);
		}else {
			for(File f:flist){
				if (f.isDirectory()) {
					iterateFolder(f, files);
				}else {
					files.add(f);
				}
			}
		}
	}
}
