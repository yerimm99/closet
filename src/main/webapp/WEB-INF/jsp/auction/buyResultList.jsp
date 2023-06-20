<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>경매 구매 내역 조회</title>
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

        .changeBtn, .deleteBtn {
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

        .changeBtn:hover {
            background-color: #99cd89;
        }
        .deleteBtn:hover {
            background-color: #C24E4E;
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
    <jsp:include page="../menu.jsp" />
    <hr>

    <div class="container">
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
                        <c:forEach items="${bidList.pageList}" var="bid" varStatus="bidStatus">
                            <c:if test="${prodStatus.index == bidStatus.index}">
                                <tr>
                                    <td width="250px" height="250px">
                                        <img src="../../upload/${prod.picture1}" class="product-image" />
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
                                    <td>
                                        <div class="btn-group">
                                            <c:choose>
                                                <c:when test="${bid.bidResult == 0}">
                                                    입찰중<br>
                                                    <a href="<c:url value='/bid/editBid.do'><c:param name='productId' value='${prod.productId}' /></c:url>"
                                                        class="changeBtn">입찰가 수정</a>
                                                    <a href="<c:url value='/bid/deleteBid.do'><c:param name='productId' value='${prod.productId}' /></c:url>"
                                                        class="deleteBtn">입찰 취소</a>
                                                </c:when>
                                                <c:when test="${bid.bidResult == 1}">
                                                    낙찰<br>
                                                    <a href="<c:url value='/order/registerForm.do'><c:param name='productId' value='${prod.productId}' /></c:url>"
                                                        class="btn">주문 하기</a>
                                                </c:when>
                                                <c:when test="${bid.bidResult == 2}">
                                                    낙찰 실패
                                                </c:when>
                                                <c:when test="${prod.status == 0}">
                                                    주문 완료
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
   
    <!-- 이전 페이지 버튼 -->
    <c:if test="${productList.page > 0}">
        <form action="/myPage/buyAuction2.do?pageName=previous" method="get">
            <input type="hidden" name="pageName" value="previous">
            <input type="submit" value="Previous">
        </form>
    </c:if>
    
    ${productList.page + 1}
    <!-- 다음 페이지 버튼 -->
    <c:if test="${productList.page + 1 < productList.pageCount}">
        <form action="/myPage/buyAuction2.do?pageName=next" method="get">
            <input type="hidden" name="pageName" value="next">
            <input type="submit" value="Next">
        </form>
    </c:if>
</div>
</body>
</html>
