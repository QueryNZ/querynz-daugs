package com.jeecms.common.hibernate4;

import static org.junit.Assert.*;

import org.junit.Test;

public class FinderTest {

	@Test
	public void testgetOrigHql() {
		Finder finder = Finder.create("from User");
		System.out.println(finder.getOrigHql());
	}
	
//	@Test
//	public void testwrapProjection() {
//		Finder finder = new Finder();
//		System.out.println(finder.wrapProjection("select from user"));
//	}
	
	@Test
	public void testgetRowCountHql() {
		Finder finder = Finder.create("select distinct p FROM BookType join fetch p");
		System.out.println(finder.getRowCountHql());
	}
	

}
