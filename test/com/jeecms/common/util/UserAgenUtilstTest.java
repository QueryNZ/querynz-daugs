package com.jeecms.common.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jeecms.common.util.Zipper.FileEntry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:WebContent/WEB-INF/config/application-context.xml"})  
public class UserAgenUtilstTest {
	
	
	 //模拟request,response  
    private MockHttpServletRequest request;  
    private MockHttpServletResponse response;  
      
  
    private UserAgenUtils UserAgenUtils;
    // 如果是测试service 这里声明的就应该是service。  是不是？
      
    /* 
     * 测试开始之前进行初始化 
     */  
    @Before  
    public void setUp() throws Exception {  
        request = new MockHttpServletRequest();  
        request.setCharacterEncoding("UTF-8");  
        response = new MockHttpServletResponse();  
    }  
  
    @Test  
    public void testgetClientOS() {  
        request.setParameter("username", "admin");  
        request.setParameter("password", "123456");  
        try {  
            //判断控制器执行后是否返回字符串"/index"用于渲染  
            assertEquals("Win j", UserAgenUtils.getClientOS(request));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
