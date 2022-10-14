package edu.kh.jsp.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.common.JDBCTemplate;
import edu.kh.jsp.student.model.service.StudentService;
import edu.kh.jsp.student.model.vo.Student;

@WebServlet("/student/selectAll")
public class SelectAllServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// System.out.println(JDBCTemplate.getConnection());
		// --> OJDBC 연결 확인 
		
		
		// 서비스 객체 생성
		StudentService service = new StudentService();
		
		
		try {
			
			// 학생 전체 리스트를 조회하는 서비스를 호출하고 결과 반환 받기
			List<Student> stdList = service.selectAll();
			
			// 조회 결과를 요청 위임하기 위한 요청값 세팅
			req.setAttribute("stdList", stdList);
			
			// 요청 위임
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/student/selectAll.jsp");
			dispatcher.forward(req, resp);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
