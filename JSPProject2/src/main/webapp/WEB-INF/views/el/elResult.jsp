<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Person 클래스를 import -->
<%-- <%@ page import="edu.kh.jsp.model.vo.Person" %> --%> 

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EL테스트 결과</title>
</head>
<body>
	<h3>EL을 이용한 값 출력</h3>
	
	<h4>파라미터</h4>
	<pre>
	EL로 request에 세팅된 파라미터를 얻어오는 방법
	$ { para.name속성값 }
	</pre>
	
	이름 : ${ param.inputName } <br>
	나이 : ${ param.inputAge }세 <br>
	주소 : ${ param.inputAddress } 
	
	<h4>추가 세팅된 값</h4>
	<pre>
	EL을 이용해서 request에 추가로 세팅된 값을 출력할 때,
	1) 별도의 다운 캐스팅이 필요없음 ! 
	2) import 구문 생략가능
	3) 객체에 저장된 값을 얻어올 때 getter를 호출하는데 get필드명()이 아닌 필드명만 작성
	4) 데이터 파싱도 자동
	
		
	[작성방법] $ { 세팅한KEY값 }
 	</pre>	

	메세지 : ${ message } <br>
	
	person객체의 name : ${ person.name } <br>
	person객체의 age : ${ person.age } <br>
	person객체의 address : ${ person.address } <br>
	
	person객체의 toString() : ${ person } <br>


	<hr>
    <h3>스클립틀릿과 표현식을 이용한 값 출력</h3>
    
    <% 
    
    // 파라미터 얻어오기
    String name = request.getParameter("inputName");
    String age = request.getParameter("inputAge")+"세";
    String address = request.getParameter("inputAddress");
    
    // 추가 세팅된 값 얻어오기
    String message = (String)request.getAttribute("message");
    // Person person = (Person)request.getAttribute("person");    // person객체를 위한 import 생성 필요
    %>
    
    <h4>파라미터</h4>
    이름 : <%= name %> <br>
    나이 : <%= age %><br>
    주소 : <%= address %> <br>
    
    <h4>추가 세팅 값</h4>
    메세지 : <%= message %> <br>
<%--     
	Person name : <%= person.getName() %>  <br>
    Person age : <%= person.getAge() %>  <br>
    Person address : <%= person.getAddress() %>  <br>
    person.toString : <%= person.toString() %>   
--%>
    
    
    
    
    
    <hr>
    <h3>EL에서 null과 '비어있다'에 대한 처리 방법</h3>
    <p> null은 참조하는 객체가 없는 것 </p>
    <p> 비어있는 것은 참조하는 객체는 있지만 객체 내에 존재하는 내용(요소)이 없는 것 </p>
   
    <h4> empty : EL에서 비어있거나, null인지 검사하는 연산자 </h4>

    <h4>\${값 == null} OR \${ 값 eq null} : null인지 검사하는 방법</h4>
    <h4>\${값 != null} OR \${ 값 ne null} : null이 아닌지 검사하는 방법</h4>
   
    <pre>

    <b>list2</b>
        list2 == null : ${list2 == null} 
        list2 eq null : ${list2 eq null} 
        list2 != null : ${list2 != null} 
        list2 ne null : ${list2 ne null} 
        -> list2는 null이고 비어있는 리스트

        empty list2 : ${empty list2}
        -> empty가 null도 비었다고 판단


    <b>list3</b>
        list3 eq null : ${list3 eq null}
        empty list3 : ${empty list3}
        -> list3은 null은 아니지만 비어있는 리스트

        Q. list에 요소가 추가되어 있는가?
        !empty list3 : ${!empty list3}
        not empty list3 : ${not empty list3}
        -> list3은 요소가 없는 비어있는 리스트!

    <b>list4</b>
        list4에 요소가 추가되어있는가? ${!empty list4}
        list4의 0번 인덱스에 존재하는 값은 ? ${ list4[0] }
        -> EL은 List에 존재하는 요소를 얻어올 때 
           배열처럼 [index번호]를 입력해서 얻어온다

    </pre>
    

</body>
</html>