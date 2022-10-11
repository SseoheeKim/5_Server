package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* @WebServlet("요청주소")
  해당 클래스를 Servlet으로 등록하고 (<Servlet>)  +  어떤 요청 주소를 처리할지 지정 (<Servlet-mapping>)
  -> web.xml에 작성해야할 내용을 한번에 작성할 수 있는 어노테이션
  -->  @WebServlet 어노테이션이 자동 완성 
*/

@WebServlet("/signUp")
public class ServletEx3 extends HttpServlet {
	
	
	// doPost() : post방식 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Post방식은 문자 데이터가 깨질 위험이 존재
		// WHY? 브라우저<->서버 간 문자 인코딩 차이 때문에
		// 해결방법 : 요청 정보에 담긴 데이터의 문자 인코딩을 UTF-8로 변경
		req.setCharacterEncoding("utf-8");
		
		
		String inputId = req.getParameter("inputId");
		String inputPw[] = req.getParameterValues("inputPw");
		// name속성이 같은 input 태그의 값은 String[] 배열 하나로 얻어올 수 있다
		String inputEmail = req.getParameter("inputEmail");
		String agree = req.getParameter("agree");
		
		System.out.println(inputId);
		System.out.println(inputPw[0]);
		System.out.println(inputPw[1]);
		System.out.println(inputEmail);
		System.out.println(agree);
		
		
		
		// 응답 데이터의 문서 형식, 문자 인코딩 지정
		resp.setContentType("text/html; charset=utf-8");
		
		
		// 응답을 위한 스트림 얻어오기
		PrintWriter out = resp.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head> <title> 회원 가입 결과 </title> </head>");
		out.print("<body>");
		if(inputPw[0].equals(inputPw[1])) {
			// 비밀번호가 같을 경우, 정상 가입된 경우에 대한 화면 
			// user01님, 환영합니다!
			// 이메일 수신 동의 여부 : O 또는 X 
			// 수신할 이메일 : user01@gmail.com (동의 O인 경우) 
			out.printf("<h3>%s님, 환영합니다!</h3>", inputId);
			
			// checkbox는 별도의 value가 없을 때
			// 체크O : "on"
			// 체크X : null
			if(agree != null) {
				out.println("<h3>이메일 수신 동의 여부 : O</h3>");
				out.printf("<h3> 수신 이메일 : %s</h3>", inputEmail);
			} else {
				out.println("<h3>이메일 수신 동의 여부 : X</h3>");
			}
			
		} else {
			// 비밀번호가 다를 경우
			out.print("<h1 style=\"color:red\";>비밀번호가 일치하지 않습니다.</h1>");
			
		}
		out.print("</body>");
		
		
		out.print("</html>");
	}

}
