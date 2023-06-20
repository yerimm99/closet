<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>공동구매 판매 내역 조회</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		.layout{margin:0px auto;width:1180px;padding:10px;font-size:18px}
		.page{text-align:center;font-size:24px;margin-top:60px;margin-bottom:30px}
		table{width:100%}
		th,td{border-top:1px solid gray;}
		.text-center{margin-top:60px;text-align:center;}
		.text-center a{margin:0px 20px; display:inline-block}
		
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<div class = "page">판매 내역 조회</div>
		<div class="row">
		<table>
			<tr>
				<th colspan = "2">상품명</th>
				<th>기능</th>
			</tr>
			 <c:forEach items="${productList.pageList}" var="prod">
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
						<a href = "<c:url value='/groupbuy/detail.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>"><b style = "font-size:18px">상세보기</b>
						</a><br>
						<a href = "<c:url value='/groupbuy/update.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>"><b style = "font-size:18px">수정하기</b>
						</a><br>
						<a href = "<c:url value='/groupbuy/delete.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>"><b style = "font-size:18px">삭제하기</b>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		 <!-- 이전 페이지, 다음 페이지 버튼 -->
    <form action="/myPage/sellGroupbuy2.do?pageName=previous" method="get">
        <input type="hidden" name="pageName" value="previous">
        <input type="submit" value="Previous">
    </form>

    <form action="/myPage/sellGroupbuy2.do?pageName=next" method="get">
        <input type="hidden" name="pageName" value="next">
        <input type="submit" value="Next">
    </form>
	</div>
	</div>
</body>
</html>