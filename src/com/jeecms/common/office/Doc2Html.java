package com.jeecms.common.office;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import antlr.debug.ParserEventSupport;

/**
 * 将word文档转换为html字符串的工具类 哪里会用到？
 * 
 * @author QueryNZ
 * @date 2017年5月25日 下午12:37:28
 * 测试ok
 */
public class Doc2Html {

	
	/**
	 * 将work文档转换为html文档
	 * 2017年5月25日 下午12:44:31
	 * ps: 哪里会用到？转换路径必须制定路径名，不能在磁盘目录下
	 * @param docFile 需要转换的word文档
	 * @param filepath 转换之后的html路径,必须制定目录，不能在磁盘根目录下
	 */
	public static File convert(File docFile, String filepath) {
		File htmlFile = new File(filepath + "/" + new Date().getTime() + ".html");
		// 创建Openoffice链接
		OpenOfficeConnection conn = new SocketOpenOfficeConnection(8100);
		try {
			// 链接
			conn.connect();
			System.out.println("获取OpenOffice链接成功...");
		} catch (ConnectException e) {
			System.out.println("获取OpenOffece链接失败...");
			e.printStackTrace();
		}
		DocumentConverter converter = new OpenOfficeDocumentConverter(conn);
		converter.convert(docFile, htmlFile);
		conn.disconnect();
		return htmlFile;
	}
	
	/**
	 * 将word转为htm文件，并且获取htm文件代码
	 * 2017年5月25日 下午4:35:57
	 * ps:
	 * @param docFile 需求转换的文档
	 * @param filepath 文档中图片保存的位置
	 * @return 转换成功的htm代码
	 */
	public static String toHtmlString(File docFile,String filepath){
		// 转换为word文档
		File htmlFile = convert(docFile, filepath);
		// 获取html文件流
		StringBuffer htmlSb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(htmlFile), "gb2312"));
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

}
