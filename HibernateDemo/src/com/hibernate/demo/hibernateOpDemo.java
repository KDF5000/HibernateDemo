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
		//Hibernate�ĳ־û�����
		try{
			session = HibernateUtil.getSession();  //��ȡsession
			session.beginTransaction();//��ʼ����
			session.save(aUser); //ִ����Ӳ���
			session.getTransaction().commit();//�ύ����
		}catch(Exception e)
		{
			session.getTransaction().rollback();//�ع�
			System.out.println("������ݿ�ʧ��");
			e.printStackTrace();
		}
		finally{
			//HibernateUtil.closeSession();//close session 
		}
	}
	
	//��ȡ�û�
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
			System.out.println("��ȡ����ʧ��");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
		return aUser;		
	}
	//ɾ��һ���û�
	public static void deleteUser(String keywords)
	{
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			User aUser = (User)session.get(User.class, keywords);  //װ�ض���
			session.delete(aUser);  //ɾ���־û�����
			session.flush();         //ǿ���ύ
		}catch(Exception e)
		{
			System.out.println("ɾ��ʧ��");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
	//�޸��û���Ϣ
	public static void setUser(User aUser)
	{
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			User user = (User)session.get(User.class, aUser.getUserName());  //װ�ض���
			user.setPassword(aUser.getPassword());  //�޸ĳ־û���
			user.setMail(aUser.getMail());
			session.flush();         //ǿ���ύ
		}catch(Exception e)
		{
			System.out.println("�޸�ʧ��");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
	//���Զ�������
	public static void testCache()
	{
		Session session1 = null;
		Session session2 = null;
		try{
			session1 = HibernateUtil.getSession();
			session2 = HibernateUtil.getSession();
			User user1 = (User)session1.get(User.class,"KDF5000");  //װ�ض���
			System.out.println("��һ��װ��");
			User user2 = (User)session1.get(User.class, "KDF5000");  //װ�ض���
			System.out.println("�ڶ���װ��");
		}catch(Exception e)
		{
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
}
