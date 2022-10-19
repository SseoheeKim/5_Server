package edu.kh.project.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.project.member.model.service.MemberService;
import edu.kh.project.member.model.vo.Member;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
	
	// 앵커태그 로그인에 대한 페이지 응답
	@Override
 	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MemberService service = new MemberService(); 
		
		try {
			
			// 파라미터 얻어오기
			String email = req.getParameter("inputEmail");
			String pw = req.getParameter("inputPw");
			
			// 멤버 객체에 파라미터 저장
			Member member = new Member();
			member.setMemberEmail(email);
			member.setMemberPw(pw);
			
			// 로그인 서비스 호출 후 결과 반환 받기
			Member loginMember = service.login(member);
			
			
			/* *************************************************************************************
			  forward를 하는 경우
			  forward는 요청을 다른 서블릿/JSP로 위임
			  -> 어떤 요청이 위임되었는지 확인할 수 있도록 주소창에 요청 주소가 계속 남아있다      */
			  
//			 RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/common/main.jsp");
//			 ServletContext application = req.getServletContext();
//			 application.setAttribute("loginMember", loginMember);
//			 dispatcher.forward(req, resp);
			
			
			/**************************************************************************************** 
			  *** redirect(재요청) 
			  - 서블릿이 다른 주소를 다시 요청
			  - 요청에 대한 응답화면을 직접 만들지 않고 
			    다른 응답화면을 구현하는 서블릿을 요청하여 대신 화면을 만들게 하는 메서드  		 */
			
			// request scope에 속성을 추가하더라도
			// redirect를 사용하면 request는 새로 만들어져 유지되지 않는 문제가 발생

			
			// !! 해결 방법 !! session scope이용
			// 1) HttpSession 객체 얻어오기
			HttpSession session = req.getSession();
			
			String path = null; // 로그인 성공/실패에 따라 이동할 경로를 저장할 변수
			
			
			if(loginMember != null ) { // 로그인이 성공한 경우
				
				path = "/"; // 메인 페이지 
				
				// 2) Session scope에 속성 추가하기
				session.setAttribute("loginMember", loginMember);
				
				// 3) 세션 만료시간 지정 (초 단위로 지정)
				// 		-> 클라이언트가 새로운 요청을 할 때마다 초기화
				session.setMaxInactiveInterval(60*60); // 60초 * 60분 == 1시간
				
				
				// --------------------------------------------------------------------------------------------------
				// 아이디 저장 (Cookie) 
				
				/* Cookie란?
				  - 클라이언트 측(브라우저)에서 관리하는 파일
				  - 쿠키 파일에 등록된 주소 요청시마다 자동으로 요청에 첨부되어 서버로 전달
				  - 서버로 전달된 쿠키에 값 추가, 수정, 삭제 등을 진행한 후 다시 클라이언트에게 반환 */
				
				// 1) 쿠키 객체 생성
				//		- 생성된 쿠키 객체를 response를 이용해서 클라이언트에게 전달하면 
				// 		  클라이언트 컴퓨터에 파일 형태로 저장됨
				Cookie cookie = new Cookie("saveId", email);
				
				
				
				// 2) 아이디 저장 체크박스가 체크되었을 때 쿠키 생성
				if( req.getParameter("saveId") != null) { // 체크된 경우
					
					// 3) 쿠키가 유지될 수 있는 유효기간 설정(초 단위)
					cookie.setMaxAge(60 * 60 * 24 * 30); // 30일
					
					
				} else { // 체크가 안된 경우
					// 4) 쿠키의 유효기간을 0초로 설정 == 클라이언트에 저장된 saveId쿠키를 삭제
					//	-> 같은 key값의 쿠키가 저장되면 덮어쓰기로 삭제되는 것
					cookie.setMaxAge(0); 
				}
				
				// 5) 생성된 쿠키가 적용되어질 요청 주소를 지정
				cookie.setPath("/"); // 메인 페이지의 하위 주소를 모두 포함하여 모든 주소를 적용

				// 6) 설정 완료된 쿠키 객체를 클라이언트에게 전달
				resp.addCookie(cookie);
				
				// --------------------------------------------------------------------------------------------------
				
			
				
			} else { // 로그인 실패 시
				
				// 현재 요청하기 이전의 페이지
				path = req.getHeader("referer");
				// Referer 사용처
				// referer의 값은 이 페이지를 요청한 이전 페이지가 무엇인지를 알려주는 정보
				// referer는 보통 로그 분석이나 접근 제어를 할 때 이용
			
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				
			}
			
			
			// path가 "/"인 경우는 메인페이지 요청
			 resp.sendRedirect(path); 
			// 메인페이지로 응답을 재요청
			// -> 주소창의 주소가 메인페이지 주소("/")로 변함
			
			 
			 
			/* forward /  redirect 차이점 
			 
			 * forward 
			  - 주소창 변화 X
			  - 요청 주소에 JPS경로를 작성
			  - request, response가 그대로 유지
			  
			 * redirect
			  - 주소창 변화 O
			  - 요청 주소를 작성
			  - request, response 유지되고 새롭게 만들어진다
			 */
			
			
				
			
		} catch(Exception e) {
			e.printStackTrace();

			String errorMessage = "로그인 중 문제가 발생했습니다";
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("e", e);
			
			String path = "/WEB-INF/views/common/error.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	
			
	}
}
