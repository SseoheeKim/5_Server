package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.model.vo.Person;


@WebServlet("/elTest")
public class ELTestServlet extends HttpServlet {
	/* ***********************************************************************************
	  get, post 등 데이터 전달 방식에 따라서 하나의 요청 주소로 여러가지 처리가 가능하다
	 ************************************************************************************* */ 
	
	
	// a태그로 요청(get방식)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/el/elTest.jsp");

		// WEB-INF 폴더는 외부 접근 방법에 의해서는 직접 접근이 불가능한 protected 폴더
		// 단, 내부 접근 방법(Servlet, JSP)에 의한 직접 접근은 가능
		
		dispatcher.forward(req, resp);
		
	}
	
	
	// form태그 요청(post방식)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 얻어오기
		String name = req.getParameter("inputName");
		int age = Integer.parseInt(req.getParameter("inputAge"));
		String address = req.getParameter("inputAddress");
		
		String message =  name + "님은 " + age + "세이고 " + address + "에 거주중 입니다.";
		
		
		// 기본생성자로 Person 객체 생성
		Person p = new Person();
		p.setName(name + "님");
		p.setAge(age);
		p.setAddress("대한민국 " + address);
		

		
		
		// 요청 위임을 위한 발송자 생성
															// webapp폴더 기준으로 jsp 파일 경로 작성
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/el/elResult.jsp");
		
		
		// 요청 위임 시 추가할 값 세팅
		req.setAttribute("message", message);
		req.setAttribute("person", p);
		
		List<String> list2 = null;	// null인 리스트
		
		List<String> list3 = new ArrayList<>(); // 비어있는 리스트
		
		List<String> list4 = new ArrayList<>(); // 값이 있는 리스트
		list4.add("테스트4");
	
		req.setAttribute("list2", list2);
		req.setAttribute("list3", list3);
		req.setAttribute("list4", list4);

		
		// JSP로 요청 위임
		dispatcher.forward(req, resp);
		
	}
	
}
