package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pizzaOrder")
public class PizzaOrderServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        
        String size = req.getParameter("size");
        
        int amount = Integer.parseInt(req.getParameter("amount")) ;  
        // Integer.parseInt("문자열") : 숫자 형태의 String을 int형으로 형변환
        
        
        int temp = 0;
        if(size.equals("L"))    temp=2000;
        
        int totalSum = ( 10000 + temp ) * amount;
        // ( 기본10,000원 + 사이즈(0/2000) ) * 수량(1~10);
        

        // JSP 요청 위임 객체 생성(JSP 경로 지정)
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/orderResult.jsp");  
                                        // 404 Not Found오류 시 확인
        
        // req에 새로운 변수 totalSum값을 세팅
        req.setAttribute("tot", totalSum);
        
        
        // req, resp객체를 JSP로 위임
        dispatcher.forward(req, resp);
        // req : 파라미터, res
        // resp : 응답용 스트림
    
    }
}
