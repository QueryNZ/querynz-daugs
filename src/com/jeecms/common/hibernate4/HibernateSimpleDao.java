package com.jeecms.common.hibernate4;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.sun.star.rendering.RepaintResult;

/**
 * hibernate DAO基类 提供hql分页查询，不带泛型，与具体实体类无关。最底层的查询接口
 * 
 * @author QueryNZ
 * @date 2017年5月24日 下午6:43:12
 */
public abstract class HibernateSimpleDao {

	
	
	/**
	 * 通过hql查询唯一对象
	 * 2017年5月26日 下午4:19:44
	 * ps: 当表中没有数据时候查询你出来的为null
	 * @param
	 */
	protected Object findUnique(String hql,Object... values){
		return createQuery(hql, values).uniqueResult();
	}
	
	
	/**
	 * 查找集合对象
	 * 2017年5月26日 下午4:18:32
	 * ps:
	 * @param
	 */
	protected List find(String hql,Object... values){
		return createQuery(hql, values).list();
	}
	
	
	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多的处理，辅助函数</br>
	 * 2017年5月25日 上午10:44:08</br>
	 * ps:描述的不够准确。此方法是辅助函数没有错，但是，就是给定查询的hql语句。利用createQuery查询返回
	 * 	     一个对象，然后进行处理。values，是进行查询以后过滤使用。</br>
	 * 测试：hql查询没有问题，但是过滤时候有问题，实际数据库里面没有值，做参数绑定没有绑定上。然而并没有报错
	 * @param queryString 查询字符串。如果由参数绑定，那么必须指定参数
	 * @param values 需要指定绑定的参数
	 */
	protected Query createQuery(String queryString,Object... values){
		// 断言查询字符串中是否需要位置参数绑定。
		Assert.hasText(queryString);
		// 进行查询
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				// 对Query对象进行位置参数绑定。
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;	
	}
	
	
	/**
	 * 工厂模式：把一切工作交给工厂处理。然后工厂给对象开放接口，用户调用工厂的功能
	 * 测试：ok
	 */
	protected SessionFactory sessionFactory;

	
	/**
	 * 自动注入会话工厂
	 * 2017年5月24日 下午6:46:17
	 * ps:测试ok
	 * @param
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 工厂开放的功能，链接工厂
	 * 2017年5月24日 下午6:45:48
	 * ps: 由于session没有链接时间的限制条件。所以获取不到，但是代码本身没有问题
	 * @param
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

}
