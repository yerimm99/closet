<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="targetUrl"><c:url value="/auction/confirmAuction.do" /></c:set>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>경매 상품 등록폼</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		table, td{border:none;border-collapse:collapse;}
		.inp{width:500px;height:35px}
		.inpText{width:500px;height:80px}
		table{margin:50px auto 0px auto}
		td{height:40px}
		input{border:none;}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<form:form modelAttribute = "auctionForm" action="${targetUrl}" method="post" >
		<table>
			<tr>
				<td style="text-align:center;font-size:24px">경매 상품 등록</td>
			</tr>
			<tr>
				<td>
					<br><br>
					<form:input path = "auction.name" placeholder = "상품명" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path = "auction.size" placeholder = "사이즈" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path = "auction.color" placeholder = "색상" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td><!-- 왜자꾸 0이 기본값으로 들어가는지 모르겠음 -->
					<form:input path = "auction.startPrice" placeholder = "최소가격"  class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<!-- 왜자꾸 0이 기본값으로 들어가는지 모르겠음 -->
					<form:input path = "auction.period" placeholder = "1일이상 100일 미만의 경매 기간" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td><!-- 이쁘게 수정 -->
					상품의 카테고리를 고르세요.
					<form:select path = "auction.categoryId" items = "${categories}"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td><!-- 이쁘게 수정 -->
					<form:radiobutton path = "auction.used" value = "0"/>
						<label>새상품</label>
					&nbsp;&nbsp;
					<form:radiobutton path = "auction.used" value = "1"/>
						<label>중고상품</label>
					
				</td>
			</tr>
			<tr>
				<td>
					<form:textarea path = "auction.description" class = "inpText"
						 placeholder = "올릴 상품에 대한 설명을 작성해주세요."/>
					<hr>
				</td>
			</tr>
			<tr>
				<td><!-- form:form태그에 file url업로드 기능 없음. requestParam으로 가져가야함 -->
					최소 1개 최대 4개의 사진을 업로드해주세요
					<form:input path = "auction.picture1" placeholder = "사진1" class = "inp"/>
					<form:input path = "auction.picture2" placeholder = "사진2" class = "inp"/>
					<!-- <input type = "file" name = "picture1" id = "picture1" multiple> -->
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="등록하기">
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>