<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>공동구매 페이지</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
		.sell{background-color:white;width:100px;height:40px;font-size:24px;text-align:center;
			padding:10px;float:left;}
		.category{background-color:white;height:60px;margin-left:250px}
		.category a{background-color:white;float:left;height:40px;font-size:18px;text-align:center;
			margin:10px 15px;text-decoration-color : gray;}
		.categories{background-color:white;margin-top:80px;font-size:15px;text-align:left;
		width:100px;padding:10px;float:left}
		.categories input{margin-bottom:15px}
		.list{background-color:white;height:60px;margin-left:250px;margin-top:80px;height:600px}
		a{display:block}
	 	a:link{text-decoration:none;color:black}
		a:visited{text-decoration:none;color:black}
		a:active{text-decoration:none;color:black}
		a:hover{text-decoration:none;color:black}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<div>
			<!-- 물건 등록 기능 -->
			<div class = "sell">
				<a href="<c:url value='/groupbuy/registerForm.do' />">등록하기</a>
			</div>
			<!-- 카테고리 -->
			<div class = "category">
				<a href="<c:url value='/groupbuy/list.do?categoryId=전체' />">전체</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=신발' />">신발</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=아우터' />">아우터</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=상의' />">상의</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=하의' />">하의</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=가방' />">가방</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=지갑' />">지갑</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=시계' />">시계</a>
				<a href="<c:url value='/groupbuy/list.do?categoryId=패션잡화' />">패션잡화</a>
			</div>
		</div>
		<!-- 중복선택가능 -->
		<div class = "categories">
			<script>
				document.frm.submit();
			</script>
			<form class = "cFrom" method = "POST" action="<c:url value='/groupbuy/list' />">
				<label>카테고리</label><br><br>
				<input type = "checkbox" name = "categoryId" value = "전체">전체<br>
				<input type = "checkbox" name = "categoryId" value = "신발">신발<br>
				<input type = "checkbox" name = "categoryId" value = "아우터">아우터<br>
				<input type = "checkbox" name = "categoryId" value = "상의">상의<br>
				<input type = "checkbox" name = "categoryId" value = "하의">하의<br>
				<input type = "checkbox" name = "categoryId" value = "가방">가방<br>
				<input type = "checkbox" name = "categoryId" value = "지갑">지갑<br>
				<input type = "checkbox" name = "categoryId" value = "시계">시계<br>
				<input type = "checkbox" name = "categoryId" value = "패션잡화">패션잡화<br>
			</form>
		</div>
		<!-- 해당 상품 리스트 -->
		<div class = "list">
			<jsp:include page = "../groupbuy/list.jsp"/>
		</div>
	</div>
</body>
</html>