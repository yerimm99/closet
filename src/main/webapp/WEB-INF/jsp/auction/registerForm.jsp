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
		.inpText{width:800px;height:80px;font-size:18px}
		.category{padding:0px}
		.btn{display:block;margin:0px auto;text-align:center;font-size:20px;border-radius:10px;background-color:black;
		border:1px solid black;width:510px;height:35px;color:white;margin-top:10px}
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
		<form:form modelAttribute = "auctionForm" action="${targetUrl}" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<c:if test="${auctionForm.newAuction}">
					<td style="text-align:center;font-size:24px" colspan = "2">경매 상품 등록<br><br></td>
				</c:if>
				<c:if test="${!auctionForm.newAuction}">
					<td style="text-align:center;font-size:24px" colspan = "2">경매 상품 수정<br><br></td>
				</c:if>
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
					<c:if test="${auctionForm.newAuction}">
						<form:input path = "auction.startPrice" class = "inp"/>
					</c:if>
					<c:if test="${!auctionForm.newAuction}">
						<c:out value="${auctionForm.auction.startPrice}" />
					</c:if>
					<hr>
				</td>
			</tr>
			<tr>
				<td>종료 날짜</td>
 				<td>
 					<c:if test="${auctionForm.newAuction}">
						<form:input path = "auction.endDate" class = "inp" placeholder = "xxxx/xx/xx형식으로 입력해주세요."/>
					</c:if>
					<c:if test="${!auctionForm.newAuction}">
						<c:out value="${auctionForm.auction.endDate}" />
					</c:if>
 					<!--<form:input type = "date" path = "auction.endDate" class = "inp" value = "2023/06/20"/>-->
 					<hr>
 				</td> 
			</tr>
		 	<tr style = "height:60px">
				<td>상품 카테고리</td>
				<td>
					<form:radiobuttons path="auction.categoryId" items = "${categories}" class = "category"/>
				</td>
			</tr>
			<tr>
				<td style = "height:50px">상품 유형</td>
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
					<hr>
					<form:textarea path = "auction.description" class = "inpText"
						 placeholder = "올릴 상품에 대한 설명을 작성해주세요."/>
					<hr>
				</td>
			</tr>
			<tr>
				<td colspan = "2"><hr></td>
			</tr>
			<tr>
				<td>사진첨부(나중에삭제)</td>
				<td><!-- form:form태그에 file url업로드 기능 없음. requestParam으로 가져가야함 -->
					최소 1개 최대 4개의 사진을 업로드해주세요<br>
					    
					<input type="file" name="files" accept="image/*" multiple />
					<!-- <input type = "file" name = "picture1" id = "picture1" multiple> -->
				</td>
			</tr>
			<tr>
				<td colspan = "2">
					<hr>
					<input type="submit" value="등록하기" class = "btn">
					<br><br><br>
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>