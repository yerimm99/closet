<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="targetUrl"><c:url value='/bid/confirmBid.do' /></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>입찰 페이지</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<h3>입찰하기</h3>
		<div>
			<div class = "prodInfo">
				<!--  이미지 구현되면 사용<img src="<c:url value='${product.picture1}'/>">-->
				<b>${product.name}</b><br>
				${product.color}<br>
				${product.size}<br><br>
			</div>
			<hr>
			<div>
			구매 희망가
			<form:form modelAttribute = "bidForm" method="POST" action="${targetUrl}">
				<form:input placeholder="희망가입력" path="bid.bidPrice" />
				 <B><form:errors path="bidPrice" cssClass="error" /></B>
				<input type = "submit" value = "입찰하기">
			</form:form>
			</div>
		</div>
	</div>
</body>
</html>