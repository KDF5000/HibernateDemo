package com.user.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.hibernate.demo.HibernateUtil;
import com.hibernate.demo.User;

/**
 * Servlet implementation class UserQuery
 */
@WebServlet("/UserQuery")
public class UserQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String userName = (String)request.getParameter("userName");
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			User aUser = (User)session.get(User.class,userName);
			request.setAttribute("aUser", aUser);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			HibernateUtil.closeSession();
		}
		
		request.setAttribute("userName", userName);
		//实现页面的跳转
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/userInfo.jsp");
		rd.forward(request, response);
	}

}
