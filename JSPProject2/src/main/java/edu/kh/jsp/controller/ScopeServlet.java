package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/scope") // @WebServlet은 무조건 절대경로로 요청주소를 작성!!
public class ScopeServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/el/scope.jsp");
		
		// 1. page scope -> JSP에서만 사용 가능
		// PageContext 추상 클래스 이용
		
		
		// 2.request scope
		req.setAttribute("message", "request scope에 저장된 메세지 입니다.");
		
		
		// 3. session scope
		// 	1) HttpSession 객체 얻어오기
		HttpSession session = req.getSession();
		//  2) session scope로 값 세팅하기
		//  * page, request, session, application은 모두 사용법이 동일
		session.setAttribute("sessionValue", "999");
		
		
		// 4. application scope
		// 1) ServletContext 객체 얻어오기
		ServletContext application = req.getServletContext();
		// 2) application 범위로 값 세팅하기
		application.setAttribute("appValue", "application의 범위 값");
		
		
		// *** 내장 객체의 우선 순위 확인 ***
		
		// page -> JSP작성
		
		req.setAttribute("str", "request scope");
		session.setAttribute("str", "session scope");
		application.setAttribute("str", "application scope");
		
		
		
		dispatcher.forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
