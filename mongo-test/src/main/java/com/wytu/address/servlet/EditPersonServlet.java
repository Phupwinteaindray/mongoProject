package com.wytu.address.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.wytu.address.dto.MongoDBPersonDAO;
import com.wytu.address.entity.Student;

@WebServlet(urlPatterns = "/editPerson")
public class EditPersonServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			throw new ServletException("id missing for edit operation");
		}
		System.out.println("Person edit requested with id=" + id);
//		MongoClient mongo = (MongoClient) request.getServletContext()
//				.getAttribute("MONGO_CLIENT");
		MongoDBPersonDAO personDAO = new MongoDBPersonDAO();
		Student p = new Student();
		p.setId(id);
		p = personDAO.readPerson(p);
		request.setAttribute("person", p);
		List<Student> persons = personDAO.readAllPerson();
		request.setAttribute("persons", persons);


		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/persons.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id"); // keep it non-editable in UI
		if (id == null || "".equals(id)) {
			throw new ServletException("id missing for edit operation");
		}

		String name = request.getParameter("name");
		String country = request.getParameter("country");
		String rollNumber=request.getParameter("rollNumber");
		String email=request.getParameter("email");
		if ((name == null || name.equals(""))
				|| (country == null || country.equals(""))) {
			request.setAttribute("error", "Name and Country Can't be empty");
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			MongoDBPersonDAO personDAO = new MongoDBPersonDAO();
			Student p = new Student();
			p.setId(id);
			p.setName(name);
			p.setCountry(country);
			p.setRollNumber(rollNumber);
			p.setEmail(email);
			request.setAttribute("person", p);
			List<Student> persons = personDAO.readAllPerson();
			request.setAttribute("persons", persons);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/persons.jsp");
			rd.forward(request, response);
		} else {
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			MongoDBPersonDAO personDAO = new MongoDBPersonDAO();
			Student p = new Student();
			p.setId(id);
			p.setName(name);
			p.setCountry(country);
			p.setEmail(email);
			p.setRollNumber(rollNumber);
			personDAO.updatePerson(p);
			System.out.println("Person edited successfully with id=" + id);
			request.setAttribute("success", "Person edited successfully");
			List<Student> persons = personDAO.readAllPerson();
			request.setAttribute("persons", persons);

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/persons.jsp");
			rd.forward(request, response);
		}
	}
	
}
