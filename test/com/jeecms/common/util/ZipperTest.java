package com.jeecms.common.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.junit.Test;

import com.ibm.db2.jcc.am.ne;
import com.jeecms.common.util.Zipper.FileEntry;

public class ZipperTest {

	@Test
	public void testZipper() throws IOException {
		List<FileEntry> fileEntry = new ArrayList<FileEntry>();
		File file = new File("f:/1.docx");
		File file2 = new File("f:/2.docx");
		fileEntry.add(new FileEntry("", "", file));
		fileEntry.add(new FileEntry("", "", file2));
		Zipper zipper = new Zipper(new FileOutputStream(new File("f:/1.zip")),fileEntry, "utf-8");
		
	}
	


}
