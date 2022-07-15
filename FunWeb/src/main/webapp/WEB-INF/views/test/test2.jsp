<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이름_통합구현구현_실기시험_파일_test2.jsp</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn').click(function(){
//	 		AjaxTestController 만들기
//	 		ajax 이용  가상주소 /test/jsonlist
//	         table 결과 출력  
			$.ajax({
				url:'${pageContext.request.contextPath}/test/jsonlist',
				dataType:'json',
				success:function(rdata){
					$.each(rdata,function(index,item){
						d=new Date(item.date);
						sdate=d.getFullYear()+"."+(d.getMonth()+1)+"."+d.getDay();
	$('table').append("<tr><td>"+item.id+"</td><td>"+item.pass+"</td><td>"+item.name+"</td><td>"+sdate+"</td></tr>");
					});
				}
			});	
		});
	});
</script>
</head>
<body>
<h3>3번)회원목록조회버튼 클릭시 회원목록 가져와서 출력하세요</h3>
<h3>회원목록조회 버튼 클릭시 테이블에 회원목록조회 보이게 하세요</h3>

<input type="button" value="회원목록조회" id="btn">
<table border="1">
<tr><td>아이디</td><td>비밀번호</td><td>이름</td><td>날짜</td></tr>
</table>
</body>
</html>