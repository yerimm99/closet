<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>공동구매 상품 등록폼</title>
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
		<form:form modelAttribute = "groupbuyForm" method="POST" action="<c:url value='/groupbuy/registerForm' />">
		<table>
			<tr>
				<td style="text-align:center;font-size:24px">공동구매 상품 등록</td>
			</tr>
			<tr>
				<td>
					<br><br>
					<form:input path = "groupbuy.name" placeholder = "상품명" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path = "groupbuy.size" placeholder = "사이즈" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<form:input path = "groupbuy.color" placeholder = "색상" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td><!-- 왜자꾸 0이 기본값으로 들어가는지 모르겠음 -->
					<form:input path = "groupbuy.price" placeholder = "가격"  class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<!-- 왜자꾸 0이 기본값으로 들어가는지 모르겠음 -->
					<form:input path = "groupbuy.peopleNum" placeholder = "공동구매 참여자 수" class = "inp"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td><!-- 이쁘게 수정 -->
					상품의 카테고리를 고르세요.
					<form:select path = "groupbuy.categoryId" items = "${categories}"/>
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<form:textarea path = "groupbuy.description" class = "inpText"
						 placeholder = "올릴 상품에 대한 설명을 작성해주세요."/>
					<hr>
				</td>
			</tr>
			<tr>
				<td><!-- form:form태그에 file url업로드 기능 없음. requestParam으로 가져가야함 -->
					최소 1개 최대 4개의 사진을 업로드해주세요
					<input type = "file" name = "picture1" id = "picture1" multiple>
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