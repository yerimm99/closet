<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>best 페이지</title>
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
		.notNew{margin-top:5px}
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
		<br>
		<a href = "<c:url value='/rank/list.do'>
			<c:param name = 'DTYPE' value='Auction'/>
			</c:url>">경매 상품
		</a>
		<a href = "<c:url value='/rank/list.do'>
			<c:param name = 'DTYPE' value='Auction'/>
			</c:url>">경매 상품
		</a>
		<br>
		<hr>
		
		<!-- 카테고리 -->
		<div class = "category">
			<c:if test = "${empty DTYPE}">
				<a href="<c:url value='/rank/list.do?categoryId=전체' />">전체</a>
				<a href="<c:url value='/rank/list.do?categoryId=신발' />">신발</a>
				<a href="<c:url value='/rank/list.do?categoryId=아우터' />">아우터</a>
				<a href="<c:url value='/rank/list.do?categoryId=상의' />">상의</a>
				<a href="<c:url value='/rank/list.do?categoryId=하의' />">하의</a>
				<a href="<c:url value='/rank/list.do?categoryId=가방' />">가방</a>
				<a href="<c:url value='/rank/list.do?categoryId=지갑' />">지갑</a>
				<a href="<c:url value='/rank/list.do?categoryId=시계' />">시계</a>
				<a href="<c:url value='/rank/list.do?categoryId=패션잡화' />">패션잡화</a>
			</c:if>
			<c:if test = "${not empty DTYPE}">
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=전체' />">전체</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=신발' />">신발</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=아우터' />">아우터</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=상의' />">상의</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=하의' />">하의</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=가방' />">가방</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=지갑' />">지갑</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=시계' />">시계</a>
				<a href="<c:url value='/rank/list.do?DTYPE=${DTYPE}&categoryId=패션잡화' />">패션잡화</a>
			</c:if>
		</div>
		
		<!-- 해당 상품 리스트 -->
		<div class = "list">
			<jsp:include page = "../rank/list.jsp"/>
		</div>
	</div>
	
</body>
</html>