package com.jeecms.common.util;

import java.awt.Color;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ZxingLogoConfig {

	/**
	 * logo默认边框颜色
	 */
	public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
	
	/**
	 * logo 默认边框宽度
	 */
	public static final int DEFAULT_BORDER = 2;
	
	/**
	 * logo 大小默认为照片的1/5
	 */
	public  static final int DEFAULT_LOGOPART = 5;
	
	
	public ZxingLogoConfig(){
		this(DEFAULT_BORDERCOLOR,DEFAULT_LOGOPART);
	}

	private final int border = DEFAULT_BORDER;
	private final Color borderColor;
	private final int logoPart;
	
	/**
	 * 
	 * @param borderColor 边框颜色
	 * @param logoPart logo占比
	 */
	
	public ZxingLogoConfig(Color borderColor, int logoPart) {
		this.borderColor = borderColor;
		this.logoPart = logoPart;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public int getBorder() {
		return border;
	}

	public int getLogoPart() {
		return logoPart;
	}
}
