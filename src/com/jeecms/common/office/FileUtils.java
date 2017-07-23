package com.jeecms.common.office;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 办公套件工具
 * @author QueryNZ
 * @date 2017年5月25日 下午5:07:41
 */
public class FileUtils {
	
	/**
	 * 获取文件前缀
	 * 2017年5月25日 下午5:09:57
	 * ps: 除了最后一个.以后的算为后缀以外，其余都是前缀
	 * @param
	 */
	public static  String getFilePrefix(String fileName){
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0,splitIndex);
	}

	/**
	 * 获取文件后缀
	 * 2017年5月25日 下午10:36:48
	 * ps:
	 * @param
	 */
	public static String getFileSufix(String fileName){
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}
	
	/**
	 * 
	 * 从路径中获取文件名
	 * 2017年5月25日 下午10:40:38
	 * ps: 找到最后一个/  和。  中间的就是  substring 不包含开始的下标
	 * @param
	 */
	public static String getFileName(String path){
		int lastIndex = path.lastIndexOf(".");
		int firstIndex = path.lastIndexOf("/");
		return path.substring(firstIndex + 1,lastIndex);
	}
	
	
	/**
	 * 获取文件路径路径
	 * 2017年5月25日 下午10:53:16
	 * ps:
	 * @param
	 */
	public static String getFilePath(String fileName){
		int splitIndex = fileName.lastIndexOf("/");
		return fileName.substring(0,splitIndex);
	}
	
	/**
	 * 显示指定目录下，指定后缀的文件列表，文件名模糊
	 * 2017年5月25日 下午11:01:42
	 * ps:
	 * @param
	 */
	public static Set<String> listFiles(File directory,String prefixFileName,String suffix){
		Set<String>filenames=new HashSet<String>();
		if(directory!=null&&directory.isDirectory()){
			File[]files = directory.listFiles();
			for(File f:files){
				String fname=f.getName();
				if(fname.endsWith(suffix)&&fname.startsWith(prefixFileName)){
					filenames.add(fname);
				}
			}
		}
		return filenames;
		
	}
	
	/**
	 * 拷贝文件
	 * 2017年5月25日 下午11:17:05
	 * ps:
	 * @param intputFile 原始文件
	 * @param outputFile 目标文件
	 * @throws FileNotFoundException 
	 */
	public static void copyFile(String intputFile,String outputFile) throws FileNotFoundException{
		File sFile = new File(intputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		try {
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				fos.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 将word转为htm文件，并且获取htm文件代码
	 * 2017年5月25日 下午4:35:57
	 * ps:
	 * @param docFile 需求转换的文档
	 * @param filepath 文档中图片保存的位置
	 * @return 转换成功的htm代码
	 */
	public static String toHtmlString(File file,String filepath){
		
		// 获取html文件流
		StringBuffer htmlSb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gb2312"));
			while (br.ready()) {
				String line = br.readLine();
				htmlSb.append(line);
			}
			br.close();
			// 删除临时文件
			// htmlFile.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String htmlStr = htmlSb.toString();
		
		return clearFormat(htmlStr,filepath);
	}

	/**
	 * 清除一些不需要的htm标记
	 * 2017年5月25日 下午4:45:42
	 * ps:  看似没有变化，但是源码里面的标签是变化的
	 * @param 带有负责htm标记的html语句
	 */
	private static String clearFormat(String htmlStr, String docImgPath) {
		// 获取body内容正则
		String bodyReg = "<BODY .*</BODY>";
		Pattern bodyPattern = Pattern.compile(bodyReg);
		//读取htm文件中的每一行，进行正则替换
		Matcher bodyMatcher = bodyPattern.matcher(htmlStr);
		if (bodyMatcher.find()) {
			// 为什么开始标记没左边的>?
			htmlStr = bodyMatcher.group().replaceFirst("<BODY", "<DIV").replaceAll("</BODY>", "</DIV>");
		}
		// 调整图片地址
		htmlStr = htmlStr.replaceAll("<IMG SRC=\"", "<IMG SRC=\"" + docImgPath + "/");
		return htmlStr;
	}

	public static String subString(String html,String prefix,String subfix) {
		return html.substring(html.indexOf(prefix)+prefix.length(), html.indexOf(subfix));
	}

	
}
