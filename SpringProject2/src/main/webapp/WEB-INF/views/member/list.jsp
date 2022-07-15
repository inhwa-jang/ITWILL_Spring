<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp4/list.jsp</title>
</head>
<body>
<%
//session "id" 가져오기
String id=(String)session.getAttribute("id");
// 세션값이 없으면 => loginForm.jsp이동
// if(id==null){
// 	response.sendRedirect("loginForm.jsp");
// }else{
// 	if(!(id.equals("admin"))){
// 		response.sendRedirect("main.jsp");
// 	}
// }

// if  for => 태그
// JSTL 프로그램 설치
// JSTL => Jsp standard Tag lib => jsp표준 태그함수 제공
%>
<c:if test="${ empty sessionScope.id }">
	<c:redirect url="/member/login"/>
</c:if>
<c:if test="${ ! empty sessionScope.id }">
	<c:if test="${ ! (sessionScope.id eq 'admin')}">
		<c:redirect url="/member/main"/>
	</c:if>
</c:if>

<table border="1">
<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>가입날짜</td></tr>
<c:forEach var="memberDTO" items="${memberList }">
	<tr><td>${memberDTO.id }</td><td>${memberDTO.pass }</td>
	    <td>${memberDTO.name }</td><td>${memberDTO.date }</td></tr>
</c:forEach>
</table>

</body>
</html>