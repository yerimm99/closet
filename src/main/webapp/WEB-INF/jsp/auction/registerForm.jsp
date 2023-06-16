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
		td{padding: 0px 15px}
		.inp{width:600px;height:35px}
		.inpText{width:600px;height:80px}
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
				<td style="text-align:center;font-size:24px" colspan = "2">경매 상품 등록<br><br></td>
			</tr>
			<tr>
				<td>상품명</td>
				<td>
					<form:input path = "auction.name" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>사이즈</td>
				<td>
					<form:input path = "auction.size" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>색상</td>
				<td>
					<form:input path = "auction.color" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>최소 가격</td>
				<td>
					<form:input path = "auction.startPrice"  class = "inp" value = "최소금액"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>경매 기간</td>
				<td>
					<!-- 왜자꾸 0이 기본값으로 들어가는지 모르겠음 -->
					<form:input path = "auction.period" class = "inp"/>
					<hr>
				</td>
			</tr>
		 	<tr>
				<td>상품 카테고리</td>
				<td>
					<form:radiobutton path = "auction.used" value = "0"/>
						<label>새상품</label>
					&nbsp;&nbsp;
					<form:radiobutton path = "auction.used" value = "1"/>
						<label>중고상품</label>
					<hr>
				</td>
			</tr>
			<tr>
				<td>상품 유형</td>
				<td><!-- 이쁘게 수정 -->
					<form:radiobutton path = "auction.used" value = "0"/>
						<label>새상품</label>
					&nbsp;&nbsp;
					<form:radiobutton path = "auction.used" value = "1"/>
						<label>중고상품</label>
				</td>
			</tr>
			<tr>
				<td colspan = "2">
					<form:textarea path = "auction.description" class = "inpText"
						 placeholder = "올릴 상품에 대한 설명을 작성해주세요."/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>사진첨부</td>
				<td><!-- form:form태그에 file url업로드 기능 없음. requestParam으로 가져가야함 -->
					최소 1개 최대 4개의 사진을 업로드해주세요<br>
					<form:input path = "auction.picture1" placeholder = "사진1" class = "inp"/><br>
					<form:input path = "auction.picture2" placeholder = "사진2" class = "inp"/>
					<!-- <input type = "file" name = "picture1" id = "picture1" multiple> -->
					<hr>
				</td>
			</tr>
			<tr>
				<td colspan = "2">
					<input type="submit" value="등록하기">
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>