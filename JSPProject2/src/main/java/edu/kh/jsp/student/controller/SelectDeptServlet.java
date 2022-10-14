package edu.kh.jsp.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.student.model.service.StudentService;
import edu.kh.jsp.student.model.vo.Student;

@WebServlet("/student/selectDepartment")
public class SelectDeptServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
		StudentService service = new StudentService();
		
		String deptName = req.getParameter("inputDept");
		List<Student> stdList = service.selectDept(deptName);
		
		
		req.setAttribute("stdList", stdList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/student/selectDept.jsp");
		dispatcher.forward(req, resp);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
				
	}
}
