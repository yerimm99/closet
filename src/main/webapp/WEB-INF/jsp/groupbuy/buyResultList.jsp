<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공동구매 구매 내역 조회</title>
    <style type="text/css">
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 960px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            font-size: 24px;
            margin: 0;
            padding: 20px 0;
            text-align: center;
            color: #333;
        }

        table {
        	text-align: center;
            width: 100%;
            background-color: #fff;
            border-collapse: collapse;
        }

        th,
        td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f9f9f9;
        }

        img {
            max-width: 100px;
            height: auto;
        }

        .btn-group {
            display: inline-block;
            padding: 8px 16px;
            border-radius: 4px;
            text-decoration: none;
            background-color: lightgray;
            color: #ffffff;
            border: none;
            transition: background-color 0.3s;
            font-size: 14px;
        }

        .product-info {
        	justify-content: center;
            display: flex;
            align-items: center;
        }

        .product-info img {
            margin-right: 10px;
        }

        .no-data {
            text-align: center;
            padding: 20px;
            color: #999;
        }
    </style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "container">
		<h1>구매 내역 조회</h1>
		 <c:choose>
            <c:when test="${empty productList.getSource()}">
                <p class="no-data">등록된 구매 내역이 없습니다.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>상품 이미지</th>
                        <th>상품 정보</th>
                        <th>결과</th>
                    </tr>
					<c:forEach items="${productList.pageList}" var="prod" varStatus="prodStatus">
		  			<c:forEach items="${meetList.pageList}" var="meet" varStatus="meetStatus">
		   			<c:if test="${prodStatus.index == meetStatus.index}">
						<tr>
							<td width = "250px" height = "250px">
								<img src = "../../upload/${prod.picture1}"><br>
							</td>
							<td>
		                       <div class="product-info">
					              <div>
					                <strong>${prod.name}</strong><br>
					            	 ${prod.color}<br>
					                 ${prod.size}<br>
					                 ${prod.price}원
					               </div>
					            </div>
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
			</c:otherwise>
        </c:choose>
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