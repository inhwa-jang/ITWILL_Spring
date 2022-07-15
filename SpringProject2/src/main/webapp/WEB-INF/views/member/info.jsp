<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/info.jsp</title>
</head>
<body>
<!-- model.addAttribute("memberDTO", memberDTO); -->
<!-- 표현언어 EL 출력, 연산자, 내장객체  -->
<img src="<%=request.getContextPath() %>/resources/img/1.jpg">
<br>
아이디 : ${memberDTO.id}<br>
비밀번호 : ${memberDTO.pass}<br>
이름 : ${memberDTO.name}<br>
가입날짜 : ${memberDTO.date}<br>
<a href="<%=request.getContextPath() %>/member/main">메인으로</a>
</body>
</html>






