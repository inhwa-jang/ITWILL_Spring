<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/content.jsp</title>
</head>
<body>
<table border="1">
<tr><td>글번호</td><td>${boardDTO.num }</td>
    <td>등록일</td><td>${boardDTO.date }</td></tr>
<tr><td>글쓴이</td><td>${boardDTO.name}</td>
    <td>조회수</td><td>${boardDTO.readcount }</td></tr>
<tr><td>글제목</td><td colspan="3">${boardDTO.subject }</td></tr>
<tr><td>파일</td><td colspan="3">
<a href="${pageContext.request.contextPath}/resources/upload/${boardDTO.file}" download>${boardDTO.file }</a>
<img src="${pageContext.request.contextPath}/resources/upload/${boardDTO.file}" width="300" height="400">
</td></tr>
<tr><td>글내용</td><td colspan="3">${boardDTO.content }</td></tr>
<tr><td colspan="4">
<input type="button" value="글수정" 
onclick="location.href='${pageContext.request.contextPath}/board/update?num=${boardDTO.num }'">
<input type="button" value="글삭제" 
onclick="location.href='${pageContext.request.contextPath}/board/delete?num=${boardDTO.num }'">
<input type="button" value="글목록" 
onclick="location.href='${pageContext.request.contextPath}/board/list'"></td></tr>
</table>	
	</body>
</html>

