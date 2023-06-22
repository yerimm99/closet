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
			margin-bottom: 70px;
			text-align:center;
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
			margin-bottom:20px
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
		.input-field2 {
			width: 50%;
			padding: 10px;
			font-size: 16px;
			border: 1px solid #ccc;
			border-radius: 5px;
			margin-bottom: 20px;
		}
		.input-field3 {
			width: 40%;
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
		
		.infoImg{width:200px;height:200px;float:left}
		.info{float:left}
		
		.order-form0 {
			border: 1px solid #ccc;
			border-radius: 5px;
			padding: 20px;
			margin-bottom:20px;
			height:200px
		}
	</style>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	    function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if (data.buildingName !== '' && data.apartment === 'Y') {
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if (extraRoadAddr !== '') {
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample4_postcode').value = data.zonecode;
	                document.getElementById("sample4_roadAddress").value = roadAddr;
	                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if (roadAddr !== '') {
	                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("sample4_extraAddress").value = '';
	                }
	
	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if (data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if (data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
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
		<div class="page-title"><h3>주문/결제</h3></div>
		<div class="order-form0">
			<div class = "infoImg">
				<img src="../../upload/${product.picture1}"  alt="Product Image" width="200px" height="200px" />
			</div>
			<div class = "info">
				<br><br><br>
				&nbsp;&nbsp;<b style = "font-size="24px">상품명 : ${product.name}</b><br><br>
				&nbsp;&nbsp;색상 : ${product.color}<br>
				&nbsp;&nbsp;사이즈 : ${product.size}<br><br>
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
						<input type="text" id="sample4_postcode" placeholder="우편번호" name = "sample4_postcode"  class="input-field2">
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="input-field3"><br>
						<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="address1" class="input-field2">
						<input type="text" id="sample4_jibunAddress" placeholder="지번주소" class="address" style="display:none"><br>
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="sample4_detailAddress" placeholder="상세주소" name="address2" class="input-field2">
						<input type="text" id="sample4_extraAddress" placeholder="참고항목" style="display:none">
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
				<button type="submit" class="submit-button" >결제</button>
			</form:form>
		</div>
	</div>
</body>
</html>
