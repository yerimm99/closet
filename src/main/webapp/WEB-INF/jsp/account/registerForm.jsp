<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입폼</title>
	<style type="text/css">
		body { margin: 0; }
		.layout { margin: 0px auto; width: 1180px; padding: 10px; }
		table, td { border: none; border-collapse: collapse; }
		table { margin: 50px auto 0px auto; }
		td { height: 40px; padding: 0px 15px; }
		input { border: none; width: 600px; height: 35px; }
		.address { width: 300px; }
		.btn { display: block; margin: 0px auto; text-align: center; font-size: 20px; border-radius: 10px; background-color: black; border: 1px solid black; width: 510px; height: 35px; color: white; margin-top: 5px; }
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
	<jsp:include page="../menu.jsp" />
	<hr>
	
	<div class="layout">
		<form:form modelAttribute="accountForm" method="post" name = "form">
			<table>
				<tr>
					<c:if test="${auctionForm.newAuction}">
					<td style="text-align: center; font-size: 24px;" colspan="2">회원가입<br><br><br></td>
					</c:if>
					<c:if test="${!auctionForm.newAuction}">
					<td style="text-align: center; font-size: 24px;" colspan="2">회원정보 수정<br><br><br></td>
					</c:if>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<c:if test="${accountForm.newAccount}">
							<form:input path="account.username" />
						</c:if>
						<c:if test="${!accountForm.newAccount}">
			            	<c:out value="${accountForm.account.username}" />
			            </c:if>
					</td>
				</tr>
				<tr>
					<th colspan="2"><hr></th>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<c:if test="${accountForm.newAccount}">
							<form:input path="account.userId" />
						</c:if>
						<c:if test="${!accountForm.newAccount}">
							<c:out value="${accountForm.account.userId}" />
						</c:if>
						</td>
				</tr>
				<tr>
					<th colspan="2"><hr></th>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><form:password path="account.password" placeholder="필수 항목입니다."/></td>
				</tr>
				<tr>
					<th colspan="2"><hr></th>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><form:password path="repeatedPassword" placeholder="필수 항목입니다."/> </td>
				</tr>
				<tr>
					<th colspan="2"><hr></th>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<input type="text" id="sample4_postcode" placeholder="우편번호" class="address" name = "sample4_postcode">
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="address"><br>
						<input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="address1" class="address">
						<input type="text" id="sample4_jibunAddress" placeholder="지번주소" class="address" style="display:none"><br>
						<span id="guide" style="color:#999;display:none"></span>
						<input type="text" id="sample4_detailAddress" placeholder="상세주소" name="address2" class="address">
						<input type="text" id="sample4_extraAddress" placeholder="참고항목" class="address">
					</td>
				</tr>
				<tr>
					<th colspan="2"><hr></th>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><form:input path="account.phone" placeholder="xxx-xxxx-xxxx 형식으로 입력해주세요." /></td>
				</tr>
				<tr>
					<th colspan="2"><hr></th>
				</tr>
				<tr>
					<td>이메일</td>
					<td><form:input path="account.email" /></td>
				</tr>
				<tr>
					<th colspan="2"><hr></th>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="가입하기" class="btn">
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>