package com.wytu.address.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wytu.address.dto.MongoDBAccountDAO;
import com.wytu.address.dto.MongoDBPersonDAO;
import com.wytu.address.entity.Account;


@WebServlet(urlPatterns = {"login"})
public class AccountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession(true);
		String path=req.getServletPath();
		if("/login".equals(path)) {
			try {
				String lgid=req.getParameter("loginid");
				String password=req.getParameter("pass");
				//Check already login
		//		Object result=getServletContext().getAttribute(lgid);
//				if(null!=result) {
//					throw new RuntimeException("Already Log in with another browser");
//				}
				Account account=new Account();
				account.setLoginId(lgid);
				account.setPassword(password);
				MongoDBAccountDAO accountDAO = new MongoDBAccountDAO();
				Account acc=accountDAO.readPerson(account);
				
				
				session.setAttribute("login", acc);
			}catch(Exception e) {
				
				req.setAttribute("message", null == e.getCause() ? e.getMessage() : e.getCause().getMessage());
				getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
				return;
			}
			
		}else if("/logout".equals(path)) {
			session.invalidate();
		}
		resp.sendRedirect(req.getContextPath().concat("/home"));
	}
}
