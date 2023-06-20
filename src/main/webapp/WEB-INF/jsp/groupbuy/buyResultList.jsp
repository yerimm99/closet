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
		<c:set value="${paging}" var="paging"></c:set>
		<div class="row">
		<table>
			<tr>
				<th colspan = "2">상품명</th>
				<th>결과</th>
			</tr>
			<c:forEach items="${productList}" var="prod" varStatus="prodStatus">
  			<c:forEach items="${meetList}" var="meet" varStatus="meetStatus">
   			<c:if test="${prodStatus.index == meetStatus.index}">
				<tr>
					<td width = "250px" height = "250px">
						<img src="<c:url value='${prod.picture1}'/>" width="250px" height="250px" />
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
		<!-- 페이징 처리 -->
		<div class="text-center">
			<!-- 이전 페이지 그룹 있나? -->
			<c:if test="${paging.hasPrevious==true }">
				<a href="?page=${preview}"><i
					class="fas fa-lg fa-angle-double-left"></i></a>
					&nbsp;
				</c:if>
			<!-- 페이지 번호 표시 -->
			<c:forEach var="pagingNumber"
				begin="${paging.nowPageGroupStartPage }"
				end="${paging.nowPageGroupEndPage }">
				<c:choose>
					<c:when test="${pagingNumber==paging.nowPage }">
						<i class="fas fa-lg fa-sort-numeric-up-alt"> <a
							href="?page=${pagingNumber-1 }"
							class="board-number-change-color abncc">${pagingNumber}</a>
						</i>
							&nbsp;
						</c:when>
					<c:otherwise>
						<i class="fas fa-lg fa-sort-numeric-up-alt"> <a
							href="?page=${pagingNumber-1 }" class="board-number-change-color">${pagingNumber}</a>
						</i>
							&nbsp;
						</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 페이지 그룹 있나? -->
			<c:if test="${paging.hasNext==true}">
				<a href="?page=${requestScope.next}"><i
					class="fas fa-lg fa-angle-double-right"></i></a>
			</c:if>
			</div>
		</div>
	</div>
	</div>
</body>
</html>