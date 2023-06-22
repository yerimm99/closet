<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>주문 페이지</title>
	<style>
		body {
			margin: 0;
			padding: 0;
			font-family: Arial, sans-serif;
		}
		
		.container {
			max-width: 800px;
			margin: 0 auto;
			padding: 20px;
		}
		
		.page-title {
			font-size: 24px;
			margin-bottom: 30px;
		}
		
		.product-info {
			display: flex;
			align-items: center;
			margin-bottom: 30px;
		}
		
		.product-image {
			width: 200px;
			height: 200px;
			object-fit: cover;
			margin-right: 20px;
		}
		
		.order-form {
			border: 1px solid #ccc;
			border-radius: 5px;
			padding: 20px;
		}
		
		.field-label {
			display: block;
			font-weight: bold;
			margin-bottom: 10px;
		}
		
		.input-field {
			width: 100%;
			padding: 10px;
			font-size: 16px;
			border: 1px solid #ccc;
			border-radius: 5px;
			margin-bottom: 20px;
		}
		
		.address-field {
			display: flex;
			align-items: center;
			margin-bottom: 20px;
		}
		
		.address-field .address-input {
			flex: 1;
			margin-right: 10px;
		}
		
		.address-field .address-button {
			padding: 10px;
			font-size: 16px;
			border-radius: 5px;
			background-color: #f9f9f9;
			border: 1px solid #ccc;
			cursor: pointer;
		}
		
		.error-message {
			color: red;
		}
		
		.total-price {
			font-size: 18px;
			font-weight: bold;
			margin-top: 20px;
		}
		
		.submit-button {
			padding: 10px 20px;
			font-size: 18px;
			border-radius: 5px;
			background-color: #007bff;
			color: #fff;
			border: none;
			cursor: pointer;
		}
		
		.submit-button:hover {
			background-color: #0056b3;
		}
	</style>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function sample4_execDaumPostcode() {
			new daum.Postcode({
				oncomplete: function(data) {
					document.getElementById('sample4_postcode').value = data.zonecode;
					document.getElementById("sample4_roadAddress").value = data.roadAddress;
					document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
					document.getElementById("sample4_detailAddress").value = data.buildingName;
				}
			}).open();
		}
	</script>
</head>
<body>
<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	<div class="container">
		<div class="page-title">주문/결제</div>
		<div class="product-info">
			<img src="../../upload/${product.picture1}"  alt="Product Image" width="200px" height="200px" /><br>
			<div>
				<h2>${product.name}</h2>
				<p>${product.color}</p>
				<p>${product.size}</p>
			</div>
		</div>
		<div class="order-form">
			<form:form modelAttribute="orderForm" action="/order/register.do" method="post">
				<div>
					<label for="order.shipToName" class="field-label">받으시는 분</label>
					<form:input path="order.shipToName" id="order.shipToName" class="input-field" />
					<form:errors path="order.shipToName" cssClass="error-message"/>
				</div>
				<div class="address-field">
					<div class="address-input">
						<label for="sample4_postcode" class="field-label">주소</label>
						<input type="text" id="sample4_postcode" name="sample4_postcode" placeholder="우편번호" class="input-field">
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="address-button">
						<input type="text" id="sample4_roadAddress" name="address1" placeholder="도로명주소" class="input-field">
<!-- 						<input type="text" id="sample4_jibunAddress" name="address" placeholder="지번주소" class="input-field"> -->
  						<input type="text" id="sample4_detailAddress" placeholder="상세주소" name="address2" class="address">
					</div>
					<form:errors path="order.shipAddress" cssClass="error-message"/>
				</div>
				<div>
					<label for="order.cardType" class="field-label">카드 타입</label>
					<form:select path="order.cardType" id="order.cardType" items="${creditCardTypes}" class="input-field" />
					<form:errors path="order.cardType" cssClass="error-message"/>
				</div>
				<div>
					<label for="order.creditCard" class="field-label">카드 번호</label>
					<form:input path="order.creditCard" id="order.creditCard" placeholder="1234-1234-1234-1234" class="input-field" /> 
					<form:errors path="order.creditCard" cssClass="error-message"/>
				</div>
				<div>
					<label for="order.expiryDate" class="field-label">만료 기간</label>
					<form:input path="order.expiryDate" id="order.expiryDate" placeholder="MM/YY" class="input-field" /> 
					<form:errors path="order.expiryDate" cssClass="error-message"/>
				</div>
				<div class="total-price">
					총 결제 금액: <fmt:formatNumber value="${product.price}" type="currency" currencyCode="KRW" />
				</div>
				<button type="submit" class="submit-button">결제</button>
			</form:form>
		</div>
	</div>
</body>
</html>
