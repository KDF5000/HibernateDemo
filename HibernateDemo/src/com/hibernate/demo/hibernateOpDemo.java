package com.hibernate.demo;
import org.hibernate.Session;
public class hibernateOpDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*User aUser = new User();
		aUser.setUserName("HibernateKDF4");
		aUser.setPassword("kong123456");
		aUser.setMail("hibernate2@123.com");
		hibernateOpDemo.saveUser(aUser);
		System.out.println("password:"+aUser.getPassword());
		//User user = hibernateOpDemo.getUser("kdf5000");
		//hibernateOpDemo.deleteUser(aUser.getUserName());
		//System.out.println("UserName:"+aUser.getUserName());	
		aUser.setPassword("123456");
		hibernateOpDemo.setUser(aUser);
		User user = hibernateOpDemo.getUser(aUser.getUserName());
		System.out.println("password:"+user.getPassword());*/
		hibernateOpDemo.testCache();
	}
	public static void saveUser(User aUser)
	{
		Session  session = null;
		//Hibernate的持久化操作
		try{
			session = HibernateUtil.getSession();  //获取session
			session.beginTransaction();//开始事物
			session.save(aUser); //执行添加操作
			session.getTransaction().commit();//提交事物
		}catch(Exception e)
		{
			session.getTransaction().rollback();//回滚
			System.out.println("添加数据库失败");
			e.printStackTrace();
		}
		finally{
			//HibernateUtil.closeSession();//close session 
		}
	}
	
	//获取用户
	public static User getUser(String key)
	{
		Session session = null;
		User aUser = null;
		try{
			session = HibernateUtil.getSession();
			//aUser = (User)session.load(User.class, key);
			aUser = (User)session.get(User.class, key);
		}catch(Exception e)
		{
			System.out.println("获取对象失败");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
		return aUser;		
	}
	//删除一个用户
	public static void deleteUser(String keywords)
	{
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			User aUser = (User)session.get(User.class, keywords);  //装载对象
			session.delete(aUser);  //删除持久化对象
			session.flush();         //强制提交
		}catch(Exception e)
		{
			System.out.println("删除失败");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
	//修改用户信息
	public static void setUser(User aUser)
	{
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			User user = (User)session.get(User.class, aUser.getUserName());  //装载对象
			user.setPassword(aUser.getPassword());  //修改持久化类
			user.setMail(aUser.getMail());
			session.flush();         //强制提交
		}catch(Exception e)
		{
			System.out.println("修改失败");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
	//测试二级缓存
	public static void testCache()
	{
		Session session1 = null;
		Session session2 = null;
		try{
			session1 = HibernateUtil.getSession();
			session2 = HibernateUtil.getSession();
			User user1 = (User)session1.get(User.class,"KDF5000");  //装载对象
			System.out.println("第一次装载");
			User user2 = (User)session1.get(User.class, "KDF5000");  //装载对象
			System.out.println("第二次装载");
		}catch(Exception e)
		{
			System.out.println("测试失败");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
}
