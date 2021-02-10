package com.wytu.address.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.wytu.address.dto.MongoDBPersonDAO;
import com.wytu.address.entity.Student;
@WebServlet(urlPatterns = "/student-upload")
@MultipartConfig
public class StudentUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MongoDBPersonDAO personDao=new MongoDBPersonDAO();
		Part part=req.getPart("file");
		try (BufferedReader buffer=new BufferedReader(new InputStreamReader(part.getInputStream()))){
			String line=null;
			List<String> stList=new ArrayList<String>();
			while(null != (line = buffer.readLine())) {
				stList.add(line);
				System.out.println(line);
			}
			personDao.saveAll(stList);
			req.setAttribute("success", "Person Added Successfully");
			List<Student> persons = personDao.readAllPerson();
			req.setAttribute("persons", persons);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/persons.jsp");
		rd.forward(req, resp);
	}
}
