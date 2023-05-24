<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>경매 페이지</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "menu.jsp"/>
	<hr>
	<div class = "layout">
		<!-- 판매하기 기능 -->
		<div class = "sell">
			<form class = "sFrom" method = "POST" action="<c:url value='/auction/registerForm' />">
				<input type = "button" >
			</form>
		</div>
		<div class = "category">
			<form class = "cFrom" method = "GET" action="<c:url value='/auction/list' />">
				
			</form>
		</div>
		<div class = "categories">
			<form class = "csFrom" method = "POST" action="<c:url value='/auction/list' />">
			</form>
		</div>
		<!-- 해당 상품 리스트 -->
		<jsp:include page = "../auction/list.jsp"/>
	</div>
</body>
</html>