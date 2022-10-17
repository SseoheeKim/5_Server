<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    JSP 액션 태그 중 forward
    - 서블릿에서 JSP로 요청 위임할 때 사용하던 메서드
      Servlet -> JSP(==Servlet)
      JSP로 요청 위임시 JSP파일 경로

    - JSP(==Servlet) -> Servlet 요청 위임 시 
      Servlet 위임 시 요청 주소 작성
--%>


<jsp:forward page="main"/>
<%-- 메인 페이지(index.jsp) 요청이 왔을 때 
    요청주소가 /main인 서블릿으로 요청 위임
    -> Servlet ==> DB연결 가능
    -> 다시 JSP위임 가능
--%>