<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>주문 페이지</title>
	<style type = "text/css">
		body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        
        .layout {
            margin: 0 auto;
            width: 800px;
            padding: 10px;
            font-size: 18px;
            text-align: center;
        }
        
        .page {
            font-size: 24px;
            margin-top: 60px;
            margin-bottom: 30px;
        }
        
        .prodInfo {
            margin-bottom: 30px;
        }
        
        .address {
            width: 300px;
        }
        
        table {
            margin: 0 auto;
            text-align: left;
            margin-top: 20px;
        }
        
        td {
            padding: 5px;
        }
        
        .btn {
            margin-top: 10px;
            padding: 10px 20px;
            font-size: 18px;
        }
        form .error-message {
		    font-size: 14px;
		    color: red;
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
    <%@ include file="../menu.jsp" %>
    <hr>
    
    <div class="layout">
        <div class="page">주문/결제</div>
        <div class="prodInfo">
            <img src="../../upload/${product.picture1}" width="200px" height="200px" /><br>
            ${product.name}<br>
            ${product.color}<br>
            ${product.size}<br>
        </div>
        <hr>
        <div class="order">
            <form:form modelAttribute="orderForm" action="/order/register.do" method="post">
                <table>
                    <tr>
                        <td style="text-align: center; font-size: 20px;" colspan="2">주문 정보</td>
                    </tr>
                    <tr>
                        <td>주문하시는 분</td>
                        <td>
                            <c:out value="${orderForm.order.billToName}" />
                        </td>
                    </tr>
                    <tr>
                        <td>받으시는 분</td>
                        <td>
                            <form:input path="order.shipToName" class="input-field" />
                            <form:errors path="order.shipToName" cssClass="error-message"/>
                        </td>
                    </tr>
                    <tr>
                        <td>주소</td>
                        <td>
                            <input type="text" id="sample4_postcode" placeholder="우편번호" class="address" name="sample4_postcode">
                            <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="address"><br>
                            <input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="address1" class="address">
                            <input type="text" id="sample4_jibunAddress" placeholder="지번주소" class="address" style="display:none"><br>
                            <span id="guide" style="color: #999; display:none;"></span>
                            <input type="text" id="sample4_detailAddress" placeholder="상세주소" name="address2" class="address">
                            <input type="text" id="sample4_extraAddress" placeholder="참고항목" class="address">
                            <form:errors path="order.shipAddress" cssClass="error-message"/>
                        </td>
                    </tr>
                    <tr>
                        <td>카드 타입</td>
                        <td>
                            <form:select path="order.cardType" items="${creditCardTypes}" class="input-field" />
                            <form:errors path="order.cardType" cssClass="error-message"/>
                        </td>
                    </tr>
                    <tr>
                        <td>카드 번호</td>
                        <td>
                            <form:input path="order.creditCard" class="input-field" /> 
                            <form:errors path="order.creditCard" cssClass="error-message"/>
                        </td>
                    </tr>
                    <tr>
                        <td>만료 기간</td>
                        <td>
                            <form:input path="order.expiryDate" placeholder="MM/YYYY" class="input-field" /> 
                            <form:errors path="order.expiryDate" cssClass="error-message"/>
                        </td>
                    </tr>
                    <tr>
                        <td>총 결제 금액</td>
                        <td><c:out value="${orderForm.order.price}" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="주문하기" class="btn">
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</body>
</html>