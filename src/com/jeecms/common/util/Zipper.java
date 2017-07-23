package com.jeecms.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.jeecms.common.util.Zipper.FileEntry;



/**
 * 制作压缩包？什么时候会用？
 * 测试ok
 * @author QueryNZ
 * @date 2017年5月26日 下午10:41:03
 */
public class Zipper {

	private static final Logger log = LoggerFactory.getLogger(Zipper.class);
	
	

	/**
	 * 制作压缩包
	 * 
	 */
	public static void zip(OutputStream out, List<FileEntry> fileEntrys,
			String encoding) {
		new Zipper(out, fileEntrys, encoding);
	}

	/**
	 * 制作压缩包
	 * 
	 */
	public static void zip(OutputStream out, List<FileEntry> fileEntrys) {
		new Zipper(out, fileEntrys, null);
	}

	/**
	 * 创建Zipper对象
	 * 
	 * @param out
	 *            输出流
	 * @param filter
	 *            文件过滤，不过滤可以为null。
	 * @param srcFilename
	 *            源文件名。可以有多个源文件，如果源文件是目录，那么所有子目录都将被包含。
	 */
	protected Zipper(OutputStream out, List<FileEntry> fileEntrys,
			String encoding) {
		Assert.notEmpty(fileEntrys);
		long begin = System.currentTimeMillis();
		log.debug("开始制作压缩包");
		try {
			try {
				zipOut = new ZipOutputStream(out);
				if (!StringUtils.isBlank(encoding)) {
					log.debug("using encoding: {}", encoding);
					zipOut.setEncoding(encoding);
				} else {
					log.debug("using default encoding");
				}
				for (FileEntry fe : fileEntrys) {
					zip(fe.getFile(), fe.getFilter(), fe.getZipEntry(), fe
							.getPrefix());
				}
			} finally {
				zipOut.close();
			}
		} catch (IOException e) {
			throw new RuntimeException("制作压缩包时，出现IO异常！", e);
		}
		long end = System.currentTimeMillis();
		log.info("制作压缩包成功。耗时：{}ms。", end - begin);
	}
	/**
	 * 压缩文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param pentry
	 *            父ZipEntry
	 * @throws IOException
	 */
	public void zip(File srcFile,FilenameFilter filter,ZipEntry pentry,String prefix) throws IOException{
		ZipEntry entry;
		if (srcFile.isDirectory()) {
			if (pentry == null) {
				entry = new ZipEntry(srcFile.getName());
			}else {
				entry = new ZipEntry(pentry.getName() + "/" +srcFile.getName());
			}
			File[] files = srcFile.listFiles(filter);
			for(File f:files){
				zip(f, filter, pentry, prefix);
			}
		}else {
			if (pentry == null) {
				entry = new ZipEntry(prefix + srcFile.getName());
			}else {
				entry = new ZipEntry(pentry.getName() + "/" + prefix
						+ srcFile.getName());
			}
			System.out.println(entry);
			FileInputStream in;
			try {
				log.debug("读取文件：{}",srcFile.getAbsolutePath());
				in = new FileInputStream(srcFile);
				try {
					zipOut.putNextEntry(entry);
					int len;
					while((len = in.read(buf)) > 0){
						zipOut.write(buf,0,len);
					}
					zipOut.closeEntry();
				}finally{
					in.close();
				} 
			} catch (FileNotFoundException e) {
				throw new RuntimeException("制作压缩包时，源文件不存在："
						+ srcFile.getAbsolutePath(), e);
			}
		}
	}
	
	
	private byte[] buf = new byte[1024];
	private ZipOutputStream zipOut;
	
	public static class FileEntry {
		private FilenameFilter filter;
		private String parent;
		private File file;
		private String prefix;

		public FileEntry(FilenameFilter filter, String parent, File file, String prefix) {
			super();
			this.filter = filter;
			this.parent = parent;
			this.file = file;
			this.prefix = prefix;
		}

		public FileEntry(FilenameFilter filter, String parent) {
			super();
			this.filter = filter;
			this.parent = parent;
		}

		public FileEntry(String parent, String prefix, File file) {
			super();
			this.parent = parent;
			this.file = file;
			this.prefix = prefix;
		}

		public ZipEntry getZipEntry() {
			if (StringUtils.isBlank(parent)) {
				return null;
			} else {
				return new ZipEntry(parent);
			}
		}

		public FilenameFilter getFilter() {
			return filter;
		}

		public void setFilter(FilenameFilter filter) {
			this.filter = filter;
		}

		public String getParent() {
			return parent;
		}

		public void setParent(String parent) {
			this.parent = parent;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

	}

}
