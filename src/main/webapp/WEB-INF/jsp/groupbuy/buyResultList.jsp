<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>공동구매 구매 내역 조회</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		.page{text-align:center;font-size:24px;margin-top:60px;margin-bottom:30px}
		table{width:100%}
		th,td{border-top:1px solid gray;}
		.btn{text-align:center;border-radius:10px;line-height:30px;
			border:1px solid black;width:100px;height:30px;margin:15px auto 0px auto;}
			
		.text-center{margin-top:60px;text-align:center;}
		.text-center a{margin:0px 20px; display:inline-block}
	
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<div class = "page">구매 내역 조회</div>
		<table>
			<tr>
				<th colspan = "2">상품명</th>
				<th>결과</th>
			</tr>
			<c:forEach items="${productList.pageList}" var="prod" varStatus="prodStatus">
  			<c:forEach items="${meetList.pageList}" var="meet" varStatus="meetStatus">
   			<c:if test="${prodStatus.index == meetStatus.index}">
				<tr>
					<td width = "250px" height = "250px">
						<img src = "../../upload/${prod.picture1}">/><br>
					</td>
					<td>
						&nbsp;&nbsp;<b>${prod.name}</b><br><br>
						&nbsp;&nbsp;${prod.color}<br>
						&nbsp;&nbsp;${prod.size}<br>
						&nbsp;&nbsp;${prod.price}원
					</td>
					<td style = "text-align:center" width = "300px">
						<c:if test = "${meet.meetResult==0}">
							모집 중
						</c:if>
						<c:if test = "${meet.meetResult==1}">
							모집 완료
							<a href = "<c:url value='/order/registerForm.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>" class = "btn">주문 하기
							</a>							
						</c:if>
						<c:if test = "${meet.meetResult==2}">
							주문 실패
						</c:if>
						<c:if test = "${meet.meetResult==3}">
							주문 완료
						</c:if>
					</td>
				</tr>
			 </c:if>
 		 </c:forEach>
		</c:forEach>
		</table>
    <!-- 이전 페이지, 다음 페이지 버튼 -->
    <form action="/myPage/buyGroupbuy2.do?pageName=previous" method="get">
        <input type="hidden" name="pageName" value="previous">
        <input type="submit" value="Previous">
    </form>

    <form action="/myPage/buyGroupbuy2.do?pageName=next" method="get">
        <input type="hidden" name="pageName" value="next">
        <input type="submit" value="Next">
    </form>
	</div>
</body>
</html>