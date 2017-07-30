package com.jeecms.common.hibernate4;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Value;
import org.junit.Test;
import org.springframework.util.Assert;

import com.sun.corba.se.spi.oa.ObjectAdapterBase;

public class HibernateSimpleDaoTest {

	/**
	 * 
	 * 2017年5月24日 下午7:14:37 ps: 通过hibernate配置文件方式连接到SessionFactory
	 * 其实这里测试的是两种方式都是没有问题的，但是具体的代码其实是没有测试的。测试了也及时测试了
	 * 
	 * @param
	 */
	@Test
	public void HibernateSimpleDaoSessionFactory() {

		HibernateSimpleDao hibernateSimpleDao = new HibernateSimpleDao() {
		};

		// 读取配置文件
		Configuration cfg = new Configuration().configure();

		SessionFactory factory = hibernateSimpleDao.sessionFactory;

		Session session = null;

		factory = cfg.buildSessionFactory();

		// 测试工厂是可以的
		session = factory.openSession();
		session.disconnect();
		// session = factory.getCurrentSession();
		// 这里报错其实是对的，因为你获取一个会话时候，这个时候本身并没一个Session保持者连接，所以才会引申出来连接池的问题
		// Session session2 = hibernateSimpleDao.getSession() ;
		// session2 = factory.getCurrentSession();
//
//		if (session != null) {
//			System.out.println("链接成功....");
//		}

	}

	// 原始方法 为什么要先查寻返回对象以后然后在绑定参数？
	// query 对象返回一个bean 对象，如果不指定参数，说明需要Bean属性里面的全部属性，就不需要进行筛选了。
	// protected Query createQuery(String queryString, Object... values) {
	// Assert.hasText(queryString);
	// Query queryObject = getSession().createQuery(queryString);
	// if (values != null) {
	// for (int i = 0; i < values.length; i++) {
	// queryObject.setParameter(i, values[i]);
	// }
	// }
	// return queryObject;
	// }

	/**
	 * 参数位置绑定查询,参数可能是集合，列表，数组 2017年5月24日 下午7:36:46 ps:
	 * 
	 * @param
	 */
	@Test
	public void createQuery() {
		

		HibernateSimpleDao hibernateSimpleDao = new HibernateSimpleDao() {
		};


		Configuration cfg = new Configuration().configure();

		SessionFactory factory = hibernateSimpleDao.sessionFactory;

		Session session = null;

		factory = cfg.buildSessionFactory();

		// 测试工厂是可以的
		session = factory.openSession();

		//  这里的单元测试是由问题的。数据库是没有数据的。但是写hql语句进行参数位置绑定的时候根本没有绑定上
		//  其实这里的Object 默认对象是有值的，所以才会出现过滤失败的现象
		//  这里还需要分析不同hql语句的参数绑定都会出现什么样的情况
		String queryString = "from User bean where username=:name";
		Object[] values = {};
		Assert.hasText(queryString);
		// 进行查询
		Query queryObject = session.createQuery(queryString);
		if (values != null) {
			System.out.println(values);
			for (int i = 0; i < values.length; i++) {
				// 对Query对象进行位置参数绑定。
				queryObject.setParameter(i, values[i]);
			}
		}
	}
	
	@Test
	public void testfind(){
		HibernateSimpleDao hibernateSimpleDao = new HibernateSimpleDao() {
		};


		Configuration cfg = new Configuration().configure();

		SessionFactory factory = hibernateSimpleDao.sessionFactory;

		Session session = null;

		factory = cfg.buildSessionFactory();

		// 测试工厂是可以的
		session = factory.openSession();

		//  这里的单元测试是由问题的。数据库是没有数据的。但是写hql语句进行参数位置绑定的时候根本没有绑定上
		//  其实这里的Object 默认对象是有值的，所以才会出现过滤失败的现象
		//  这里还需要分析不同hql语句的参数绑定都会出现什么样的情况
		String queryString = "from User bean";
		Object[] values = {};
		Assert.hasText(queryString);
		// 进行查询
		Query queryObject = session.createQuery(queryString);
		System.out.println(queryObject.list());
	}
	
	@Test
	public void testfindUnique(){
		HibernateSimpleDao hibernateSimpleDao = new HibernateSimpleDao() {
		};


		Configuration cfg = new Configuration().configure();

		SessionFactory factory = hibernateSimpleDao.sessionFactory;

		Session session = null;

		factory = cfg.buildSessionFactory();

		// 测试工厂是可以的
		session = factory.openSession();

		//  这里的单元测试是由问题的。数据库是没有数据的。但是写hql语句进行参数位置绑定的时候根本没有绑定上
		//  其实这里的Object 默认对象是有值的，所以才会出现过滤失败的现象
		//  这里还需要分析不同hql语句的参数绑定都会出现什么样的情况
		String queryString = "from User bean";
		Object[] values = {};
		Assert.hasText(queryString);
		// 进行查询
		Query queryObject = session.createQuery(queryString);
		System.out.println(queryObject.uniqueResult());
	}

}
