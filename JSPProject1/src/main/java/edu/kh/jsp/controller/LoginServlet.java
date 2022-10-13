package edu.kh.jsp.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login") // web.xml에 작성한 <servlet>, <servlet-mapping> 태그 대체 어노테이션
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// post방식 요청 데이터 문자 인코딩 처리
		req.setCharacterEncoding("utf-8");
		
		// 전달 받은 파라미터를 얻어와 변수에 저장
		String id = req.getParameter("inputId");
		String pw = req.getParameter("inputPw");
		
		System.out.println(id + "/" + pw);

		// 아이디가 user01, 비밀번호가 pass01!인 경우에만 로그인 성공
		String result = null;
		if(id.equals("user01") && pw.equals("pass01!")) { 
			result = "로그인 성공";
		} else {
			result = "아이디 또는 비밀번호가 일치하지 않습니다.";
		}
		
		// 응답 형식과 문자 인코딩 지정
//		resp.setContentType("text/html; charset=utf-8;");

		// 클라이언트 응답용 스트림
//		PrintWriter out = resp.getWriter();
		
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		
//		out.println("<head>");
//		out.println("<title>로그인 결과 페이지</title>");
//		out.println("</head>");
//		
//		out.println("<body>");
//		out.println("<h1>로그인 결과</h1>");
//		out.printf("<h3 style='color:blue;'> %s </h3>", result);
//		out.print("<button type='button' onclick='history.back()'>돌아가기</button>");
//		out.println("</body>");
//		
//		out.println("</html>");
		
		
		// ***************************************************************
		// JSP로 응답하기
		
		// Dispatcher : 발송자, 필요한 정보를 제공하는 것, 역할을 위임
		// RequestDispatcher : 요청을 위임하는 역할을 하는 객체
		//						-> 요청에 대한 응답 화면을 만들어 
		//							클라이언트에게 출력하는 역할을 위임하는 객체
		
		
		/*
		  req.getRequestDispatcher("JSP 경로")
		  	- HttpServletRequest 객체가 생성될 때 
		  		내부에 요청을 위임하는 객체를 생성하는 방법을 포함
		  	
		  	
		  ** JSP 경로 작업 방법
		  - wepapp폴더를 기준으로 파일 경로를 작성 **
		  
		    ex)  /WEB-INF/views/loginResult.jsp
		  	webapp 폴더 내부에 존재하는 WEB-INF 폴더
		  	WEB-INF폴더 내부에 존재하는 views 폴더
		  	views폴더 내부에 존재하는 loginResult.jsp파일 선택
		 */
			
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp");
		
		
		/******** forward *******
		 - HttpServletRequest, HttpServletResponse를 매개변수로 전달
		 
		 - JSP가 요청 데이터(req)를 이용해서 HTML화면을 만들고
		      클라이언트에게 응답(resp)하기 위해서
		 - forward는 페이지 이동이 아닌 
		 	Servlet의 역할 중 응답 화면 출력 역할을 분업한 JSP에게
		 	req, resp를 전달만 하는 것
		 	--> JSP가 응답만 대신할 뿐, 페이지 이동이나 주소창의 요청주소도 불변!
		 */
		
		// req.setAttribute(String key, Object value)
		
		// Attribute 속성 == 데이터 값
		
		req.setAttribute("r", result);  // Object로 업캐스팅 되어 있는 상태
		dispatcher.forward(req, resp);
		// req : 요청 관련 정보 데이터(속성)
		// resp : 응답 관련 
	
	}
}
