package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx2 extends HttpServlet {

	// doGet : Get방식 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 전달된 input태그의 값 파라미터를 얻어와 변수에 저장
		String orderer = req.getParameter("orderer");
		
		// radio 버튼은 1개만 선택 가능해서 값 1개만 서버로 제출 
		String type = req.getParameter("type");
		
		// select 박스는 1개의 옵션만 선택 가능해서 값 1개만 서버로 제출
		String coffee = req.getParameter("coffee");
		
		// checkbox와 같이 하나의 name속성으로 여러 값이 전달될 경우
		// req.getParameter() -> 여러 값 중 첫 번째 값만 얻어옴 (String)
		// String opt = req.getParameter("opt");

		// req.getParameterValues() -> checkbox의 여러 값을 얻어옴 (String[])
		//							 * 단, 값이 하나도 없으면 null * 
		String[] opt = req.getParameterValues("opt");
		
		System.out.println("주문내용 정상적으로 전달 완료");
		
		// 주문자명 : 김서희
		// 주문한 메뉴 : 따뜻한 아메리카노 or 아이스 카페라떼
		// 선택한 옵션(ul, 옵션이 있을 경우만)
		// - 1샷 추가 
		// - 2샷 추가
		// - 연하게
		
		// 응답을 위해 준비해야할 것
		// 1) 문서 형식 + 문자 인코딩 지정
		resp.setContentType("text/html; charset=utf-8");
		
		// 2) 응답을 위한 서버 ---> 클라이언트 스트림 얻어오기
		PrintWriter out= resp.getWriter();
		
		// HTML코드를 응답용 스트림을 이용해 출력 시 HTML코드로 해석되어 보여짐
		// -> HTML,CSS,JS 등 브라우저가 해석할 수 있는 코드는 모두 해석되어 보여지는 것
		out.println("<!DOCTYPE html>");
		out.println("<html>");

		out.println("<head>");
		out.println("<title>" + orderer +" 님의 주문 내역</title>");
		out.println("<style>");
		out.println(
				"ul { list-style-type: none; } " + 
				"ul li:before { content: \"-\"; position: absolute; margin-left: -20px;} " );
		out.println("</style>");
		out.println("</head>");
		
		out.println("<body>");
		out.printf("<h3>주문자명 :  %s</h3>\n", orderer);
		
		out.println("<h3> 주문한 메뉴 :");
		if(type.equals("hot")) {
			out.print("따뜻한 ");
		} else {
			out.print("아이스 ");
		}
		out.println(coffee+"</h3>");
		
		if(opt!=null) {  
			out.println("<ul> ");
			for(String o : opt) {
				if(o.equals("shot1")) { // switch-case문
					out.print("<li>1샷 추가</li>\n");
				} else if(o.equals("shot2")) {
					out.print("<li>2샷 추가</li>\n");
				} else if(o.equals("shoftly")) {
					out.print("<li>연하게</li>\n");
				}
			}
			out.println("</ul>");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		/* Dynamic Web Project(동적 웹 프로젝트, 동적 웹 애플리케이션)
		 
		 	- 요청에 따라서 응답하는 화면(HTML)을 
		   	  실시간으로 만들어내서(동적)
		   	  클라이언트에게 응답하는 프로젝트
		 
		 */
		
	} 
	
}
