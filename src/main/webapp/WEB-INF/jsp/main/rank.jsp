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
		<a href = "<c:url value='/rank/auction/likeList.do'/>">경매 좋아요순</a>
		<a href = "<c:url value='/rank/auction/reviewList.do'/>">경매 리뷰순</a>
		<br>
		<a href = "<c:url value='/rank/groupbuy/likeList.do'/>">공동구매 좋아요순</a>
		<a href = "<c:url value='/rank/groupbuy/reviewList.do'/>">공동구매 리뷰순</a>
		<hr>
		
		<!-- 해당 상품 리스트 -->
		<div class = "list">
			<c:if test = "${DTYPE eq 'Auction'}">
				<jsp:include page = "../rank/auctionList.jsp"/>
			</c:if>
			<c:if test = "${DTYPE eq 'Groupbuy'}">
				<jsp:include page = "../rank/groupbuyList.jsp"/>
			</c:if>
		</div>
	</div>
	
</body>
</html>