package com.jeecms.common.util;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.INTERNAL;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 二维码图片，有关二维码的配置设置在
 * 
 * @author QueryNZ
 * @date 2017年6月2日 下午11:37:59
 * 
 * 学习：分为二维码和一维条形码，
 */
public class ZXingCode {

	private static final int BLACK = 0xFF000000; // 黑色
	private static final int WHITE = 0xFFFFFFFF; // 黑色

	private static class SingletonHolder {
		private final static ZXingCode INSTANCE = new ZXingCode();
	}

	private ZXingCode() {

	}

	public static ZXingCode getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	
	/**
	 * 构建初始化二维码
	 */
	
	
	
	
	/**
	 * 
	 * 2017年6月2日 下午11:53:03
	 * ps: 生成二维码bufferedImage图片 
	 * @param content 编码内容
	 * @param barcodeFormat 编码类型
	 * @param widht 图片宽度
	 * @param height 图片高度
	 * @param hints 设置参数
	 * 区别一维码
	 */
	public BufferedImage getQRCODEBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height,
			Map<EncodeHintType, ?> hints) {
		MultiFormatWriter multiFormatWriter = null;
		BitMatrix bm = null;
		BufferedImage image = null;
		try {
			multiFormatWriter = new MultiFormatWriter();
			
			// 参数分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
			bm = multiFormatWriter.encode(content, barcodeFormat, width, height,hints);
			
			int w = bm.getWidth();
			int h = bm.getHeight();
			
			// 开始利用二维码数据创建bitmap图片，分为设置黑白色
			for(int x= 0 ; x< w ; x++){
				for(int y = 0 ;y < h;y++){
					image.setRGB(x, y, bm.get(x,y)? BLACK:WHITE);
				}
			}
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return image;

	}
	
	
	/**
	 * 设置二维码的格式参数
	 * 2017年6月18日 下午7:43:30
	 * ps:EncodeHintType  为goole 提供的
	 * @param
	 */
	public Map<EncodeHintType, Object> getDecodHintType(){
		// 用于设置QR二维码参数
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType,Object>();
		// 设置QR二维码的纠错级别（H为最高界别）具体界别信息
		// 为什么要区分级别？为什么要纠错？
		hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H);
		// 设置编码方式
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		return hints;
		
	}
	

}
