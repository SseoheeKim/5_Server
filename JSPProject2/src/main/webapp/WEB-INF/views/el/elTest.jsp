<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1. EL</title>
</head>
<body>
    <h1>EL(Expression Language)</h1>
    <pre>
    JSP 표현식인 스크립틀릿, 익스프레션 대신 
    조금 더 효율적이고 간단히 작성할 수 있도록 고안된 표현 언어이다.

    화면에 출력하고자 하는 자바 코드를
    ${ key } 형식으로 작성하면 해당 위치에 value가 출력 

     -> 라이브러리와 함께 사용하는 다른 작성법도 존재



    ** EL의 특징 **
    1. get이라는 단어 사용 X
        EL은 화면에 표현하는 언어 
        == 출력용 언어(setting 불가)
        == get만 남음 == get 생략에도 무조건 get

    2. EL은 null을 빈칸으로 처리
        \${ null인 변수 } -> 빈칸 출력
        \${ NullPointerException 발생 코드 } -> 예외 발생없이 빈칸 출력
    </pre>

    테스트 1 : <%= request.getParameter("test") %> <br>
    테스트 2 : ${param.test} <br>

    <%-- NullPointerException 강제 발생  --%>
    <%-- <%= request.getParameter("test").equals("테스트") %> --%>
    ${param.test == '테스트'} <br>
    person.name = ${person.name} <br>
    
    <form action="/JSPProject2/elTest" method="POST">
        이름 : <input type="text" name="inputName"> <br>
        나이 : <input type="number" name="inputAge"> <br>
        주소 : <input type="text" name="inputAddress" size="50"> <br><br>
        
        <button>제출하기</button>
    </form>


    
    <ul>
        <li> 
            <%-- 요청 위임이 되지 않았기 때문에 빈칸 출력 --%>
            request scope message : ${message}
        </li>

        <li>
            session scope sessionValue : ${sessionValue}
        </li>

        <li>
            application scope appValue : ${appValue}
        </li>
    </ul>
    
    
   
</body>
</html>