package com.jeecms.common.office;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class Doc2HtmlTest {

	@Test
	public void testcovert() {
		Doc2Html doc2Html = new Doc2Html();
		doc2Html.convert(new File("F:/答辩申请.docx") , "C:/转换");
	}

	
	@Test
	public void testtoHtmlString() {
		Doc2Html doc2Html = new Doc2Html();
		doc2Html.toHtmlString(new File("F:/2.docx") , "C:/转换");
	}
}
