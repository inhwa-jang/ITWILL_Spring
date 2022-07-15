<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
<%
// 세션값 가져오기
String id=(String)session.getAttribute("id");

if(id==null){
	// 세션값 없으면(로그인 안한 상태, 세션값이 null이면)  login  join 
	%>
<div id="login"><a href="${pageContext.request.contextPath}/member/login">login</a> | 
                <a href="${pageContext.request.contextPath}/member/insert">join</a></div>	
	<%
}else{
	// 세션값 있으면(로그인 한 상태, 세션값이 null이 아니면)  ...님 logout  update 
	%>
<div id="login"><%=id %>님 <a href="${pageContext.request.contextPath}/member/logout">logout</a> | 
                <a href="${pageContext.request.contextPath}/member/update">update</a> |
                <a href="${pageContext.request.contextPath}/member/list">list</a>
                </div>	
	<%
}
%>
           
<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo">
<img src="${pageContext.request.contextPath}/resources/images/logo.gif" width="265" height="62" alt="Fun Web"></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href="${pageContext.request.contextPath}/main/main">HOME</a></li>
	<li><a href="${pageContext.request.contextPath}/company/welcome">회사소개</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>