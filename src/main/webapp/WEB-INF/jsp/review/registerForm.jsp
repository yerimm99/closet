<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="targetUrl">
	<c:url value='/bid/confirmBid.do' />
</c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>리뷰 등록 페이지</title>
	<style>
		/* KREAM 컬러 팔레트 */
		:root { -
			-kream-primary-color: #FF3366; -
			-kream-secondary-color: #333333; -
			-kream-tertiary-color: #F5F5F5;
		}
		
		body {
			margin: 0;
			font-family: 'Noto Sans', Arial, sans-serif;
			background-color: var(- -kream-tertiary-color);
		}
		
		.container {
			max-width: 600px;
			margin: 0 auto;
			padding: 20px;
			background-color: #fff;
			box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		}
		
		h3 {
			font-size: 24px;
			margin-bottom: 20px;
			color: var(- -kream-secondary-color);
			text-align: center;
			font-family: 'Pacifico', cursive;
		}
		
		.product-info {
			display: flex;
			align-items: center;
			margin-bottom: 20px;
		}
		
		.product-image {
			width: 200px;
			height: 200px;
			margin-right: 20px;
			background-color: var(- -kream-tertiary-color);
			border: 1px solid #ccc;
		}
		
		.product-details {
			flex: 1;
		}
		
		.bid-form {
			margin-top: 20px;
			text-align: center;
		}
		
		.bid-input-container {
			display: flex;
			align-items: center;
			justify-content: center;
			margin-bottom: 10px;
		}
		
		.bid-input {
			padding: 10px;
			border: 1px solid #ccc;
			width: 150px;
			margin-right: 10px;
			text-align: right;
			font-weight: bold;
			font-size: 18px;
		}
		
		.bid-input:focus {
			outline: none;
			border-color: var(- -kream-primary-color);
			box-shadow: 0 0 0 2px rgba(255, 51, 102, 0.5);
		}
		
		.submit-button {
			padding: 10px 20px;
			background-color: var(- -kream-primary-color);
			color: #fff;
			border: none;
			cursor: pointer;
			border-radius: 10px;
		}
		
		.error-message {
			color: red;
		}
	</style>
	<link
		href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap"
		rel="stylesheet">
	<link
		href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@400;700&display=swap"
		rel="stylesheet">
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page="../menu.jsp" />
	<hr>
	<div class="container">
		<h3>판매자 리뷰 작성하기</h3>
		<div class="product-info">
			<div class="product-image">
				<img src="../../upload/${product.picture1}">
			</div>
			<div class="product-details">
				<b>상품명 ${product.name}</b><br> 색상 ${product.color}<br> 사이즈
				${product.size} <br>
				<br>
			</div>
		</div>
		<hr>
		<div>
			<form method="POST" action="/review/registerForm.do">
				<div class="bid-input-container">
					<B>별점</B> &nbsp;&nbsp;&nbsp;
					<input type="text" class="bid-input" placeholder="5점이하의 실수 입력" name = "rating"/><br>
					<b>한줄평</b> &nbsp;&nbsp;&nbsp;
					<input type="text" class="bid-input" placeholder="간단하게 한줄평 써주세요." name = "content"/><br>
				</div>
				<input class="submit-button" type="submit" value="입찰하기">
			</form>
		</div>
	</div>
</body>
</html>
