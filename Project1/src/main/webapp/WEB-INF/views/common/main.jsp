<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수업용 프로젝트</title>
    
    <link rel="stylesheet" href="/Project1/resources/css/main-style.css">
</head>

<!-- fontawesome사이트의 아이콘을 이용 -->
<script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>

<body>
       
        <main>
            <header>
                <section>
                    <!-- 클릭 시 메인페이지로 이동하는 로고 -->
                    <a href="#">
                        <img src="/Project1/resources/images/logo.jpg" id="home-logo">
                    </a>
                </section>
                
                <!-- 검색창 영역  -->
                <section>
                    <article class="search-area">
                        <form action="#">
                        <!-- form : 내부 input태그의 값을 서버/페이지로 전달(제출) -->
                            <fieldset>
                                <input type="text" id="query" name="query" placeholder="검색어를 입력해주세요.">
                                <button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass"></button>
                            </fieldset>
                        </form>
                    </article>
                </section>

                <section></section>
            </header>

            <nav >
                <ul>
                    <c:forEach var="boardType" items="${boardTypeMap}">
                    <%--
                         EL을 이용한 Map 데이터를 다루는 방법
                         key   ==> ${var명.key}
                         value ==> ${var명.value}
                     --%>
                        <li><a href="/board/${boardType.key}/list">${boardType.value}</a>
                        </li>
                
                    </c:forEach>
                </ul>
            </nav>
            
            <section class="content">
                <section class="content1"></section>
                <section class="content2"> 
                    <!-- JS와 연동하기 위해 name 사용 -->
                    <form action="#" name="login-frm">
            
                        <!-- fieldset -> 아이디, 비밀번호, 로그인 버튼 -->
                        <fieldset id="id-pw-area">
                            <section>
                                <input type="text" id="inputId" name="inputId" autocomplete="off" placeholder="아이디">
                                <input type="password" id="inputPw" name="inputPw" autocomplete="off" placeholder="비밀번호">
                            </section>
            
                            <section>
                                <button type="submit">로그인</button>
                            </section>
                        </fieldset>
            
                        <!-- 로그인하기 -->
                        <!-- label태그 내부에 input태그를 작성하면 자동 연결-->
                        <label>
                            <input type="checkbox" name="saveId"> 아이디 저장
                        </label>
            
                        <!-- 회원가입/ ID/PW찾기 -->
                        <article id="signUp-find-area">
                            <a href="#">회원가입</a>
                            <span>|</span>
                            <a href="#">ID/PW찾기</a>
                        </article>
                    </form>
                </section>
            </section>
        </main>  


        <footer>
            <p> 
                Copyright &copy; KH Information Educational Institute A-Class
                <!-- &copy;  저작권 마크 -->
            </p>
            <article>
                <a href="#">프로젝트 소개</a>
                <span>|</span>
                <a href="#">이용약관</a>
                <span>|</span>
                <a href="#">개인정보처리방침</a>
                <span>|</span>
                <a href="#">고객센터</a>
            </article>
        </footer>
</body>
</html>