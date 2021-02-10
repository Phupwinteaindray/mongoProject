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

@WebServlet(urlPatterns = "/deletePerson")
public class DeletePersonServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			throw new ServletException("id missing for delete operation");
		}
//		MongoClient mongo = (MongoClient) request.getServletContext()
//				.getAttribute("MONGO_CLIENT");
		MongoDBPersonDAO personDAO = new MongoDBPersonDAO();
		Student p = new Student();
		p.setId(id);
		personDAO.deletePerson(p);
		System.out.println("Person deleted successfully with id=" + id);
		request.setAttribute("success", "Person deleted successfully");
		List<Student> persons = personDAO.readAllPerson();
		request.setAttribute("persons", persons);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/persons.jsp");
		rd.forward(request, response);
	}
	
}
