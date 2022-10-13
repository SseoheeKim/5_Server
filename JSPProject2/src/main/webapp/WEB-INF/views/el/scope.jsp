<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>2. Servlet/JSP 내장 객체와 범위(scope)</title>
</head>
<body>
    <h1>Servlet/JSP 내장 객체와 범위</h1>
    <pre>
    Servlet/JSP에는 기본적으로 내장되어있는 4종류의 객체가 존재하고,
    4종류의 객체는 각각 영향을 미칠 수 있는 범위가 다르다.


    1. page : 현재 페이지
            -> 현재 Servlet 또는 JSP에서만 사용 가능 (1 페이지)

    2. request : 요청 받은 페이지(Servlet/JSP)와
                 요청을 위임 받은 페이지(Servlet/JSP)에서 사용 가능
                 (최소 2페이지 이상) 

    3. session : 현재 사이트에 접속한 브라우저당 1개씩 생성
                브라우저가 종료되거나 session이 만료될 때까지 유지
                (세션에 로그인 정보가 기록
                -> 브라우저가 종료되거나 로그아웃 되기 전까지 계속 로그인 상태유지)

    4. application : 하나의 웹 애플리케이션 당 1개만 생성되는 객체          
                    -> 서버 시작 시 생성되고 종료까지 유지
                    누구든 사용 가능(static)


    ****************************************************************************
    ***************************** 내장 객체 우선 순위 **************************
    ****************************************************************************

    -> setAttribute("key", value)로 내장 객체가 값을 세팅할 때
       key값이 중복되는 경우

       \${key}로 작성하는 경우 범위가 가장 작은 내장 객체가 높은 우선순위
       page > request > session > application

    </pre>
    

    <ul>
        <li>
            <%
                pageContext.setAttribute("pageMsg", "page 범위 입니다.");
            
                pageContext.setAttribute("str", "page cope");
            %>
            page scope pageMsg : ${ pageMsg }

        </li>
        <li> 
            request scope message : ${message}
        </li>

        <li>
            session scope sessionValue : ${sessionValue}
        </li>

        <li>
            application scope appValue : ${appValue}
        </li>
    </ul>

    <h1>우선 순위 확인 : ${str} </h1> 

    <h3>page의 str의 값 : ${pageScope.str}</h3>
    <h3>request의 str의 값 : ${requestScope.str}</h3>
    <h3>session의 str의 값 : ${sessionScope.str}</h3>
    <h3>application의 str의 값 : ${applicationScope.str}</h3>
    
</body>
</html>