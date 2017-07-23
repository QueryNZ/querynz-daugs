package com.jeecms.common.web.freemarker;


/**
 * 模板驱动工具，用于前端后段对接，但是这个应该怎么测试？
 * @author QueryNZ
 * @date 2017年6月2日 下午11:27:14
 */
public abstract class DirectiveUtils {

	/**
	 * 输出参数：对象数据
	 */
	public static final String  OUT_BEAN = "tag_bean";

	/**
	 * 输入参数：列表数据
	 */
	public static final String  OUT_LIST = "tag_list";
	
	/**
	 * 输入参数：分页数据
	 */
	public static final String OUT_PAGINATION = "tag_pagination";
	
	
	/**
	 * 参数：是否调用模板
	 */
	public static final String PARAM_TPL = "tpl";
	
	
}
