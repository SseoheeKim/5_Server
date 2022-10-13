<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 가입 결과</title>
</head>
<body>
    <%--
        경우 1) 입력한 비밀번호가 일치하지 않는 경우
                -> 비밀번호가 일치하지 않습니다. 

        경우 2) 일치하는 경우
            아이디 : user01
            비밀번호 : pass01
            이름 : 김길동
            이메일 : user01@gmail.com
            취미 : 영화 러닝 

            김길동님, 회원가입이 완료되었습니다.
     --%>

     <%  // 스크립틀릿  -> 자바 코드 작성 영역
        String memId = request.getParameter("memId");
     
        String[] memPw = request.getParameterValues("memPw");
     
        String memName = request.getParameter("memName");
        String memEmail = request.getParameter("memEmail");
        
        String[] hobby = request.getParameterValues("hobby");
     %>

	<% if( !memPw[0].equals(memPw[1])) { %>
		<h3 style='color:blue;'>비밀번호가 일치하지 않습니다.</h3>
	<% } else { %>
		<ul>
			<li>아이디 : <%= memId %></li>
			<li>비밀번호 : <%= memPw[0] %></li>
			<li>이름 : <%= memName %></li>
			<li>이메일 : <%= memEmail %></li>
			<li>취미 :
				<%if(hobby != null) { %>
					<% for (String h : hobby){ %>
						<%= h  %>
					<% } %>
				<% } else {%>
					없음
				<% } %>	
			</li>
		</ul>
		<h3><%=memName%>님, 회원가입이 완료되었습니다.</h3>
	<% } %>
	
</body>
</html>