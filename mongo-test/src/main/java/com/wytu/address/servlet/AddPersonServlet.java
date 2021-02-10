package com.wytu.address.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wytu.address.dto.MongoDBPersonDAO;
import com.wytu.address.entity.Student;

@WebServlet(urlPatterns = { "/addPerson" })
public class AddPersonServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String country = request.getParameter("country");
		String email=request.getParameter("email");
		String rollNumber=request.getParameter("rollNumber");
		if ((name == null || name.equals("")) || (country == null || country.equals(""))) {
			request.setAttribute("error", "Mandatory Parameters Missing");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/persons.jsp");
			rd.forward(request, response);
		} else {
			Student p = new Student();
			p.setCountry(country);
			p.setName(name);
			p.setEmail(email);
			p.setRollNumber(rollNumber);
//			MongoClient mongo = (MongoClient) request.getServletContext()
//					.getAttribute("MONGO_CLIENT");
			MongoDBPersonDAO personDAO = new MongoDBPersonDAO();
			personDAO.createPerson(p);
			System.out.println("Person Added Successfully with id=" + p.getId());
			request.setAttribute("success", "Person Added Successfully");
			List<Student> persons = personDAO.readAllPerson();
			request.setAttribute("persons", persons);

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/persons.jsp");
			rd.forward(request, response);
			
			
		}
	}
}
