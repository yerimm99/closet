<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
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

        .layout {
            margin: 0 auto;
            width: 1180px;
            padding: 10px;
            font-size: 18px;
            background-color: #ffffff;
        }

        .page {
            text-align: center;
            font-size: 24px;
            margin-top: 40px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border-top: 1px solid gray;
            padding: 10px;
        }

        th {
            text-align: left;
            font-weight: bold;
        }

        td {
            vertical-align: middle;
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
            background-color: #45a049;
        }
        .deleteBtn:hover {
            background-color: red;
        }

        .text-center {
            margin-top: 40px;
            text-align: center;
        }

        .text-center a {
            margin: 0px 10px;
            display: inline-block;
            color: #000000;
            text-decoration: none;
        }
        
        .product-image {
            width: 150px;
            height: 150px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<!-- 메뉴바 -->
<jsp:include page="../menu.jsp"/>
<hr>

<div class="layout">
    <div class="page">구매 내역 조회</div>
    <table>
        <tr>
            <th>상품 사진</th>
            <th>상품 정보</th>
            <th>결과</th>
        </tr>
        <c:forEach items="${productList.pageList}" var="prod" varStatus="prodStatus">
            <c:forEach items="${bidList.pageList}" var="bid" varStatus="bidStatus">
                <c:if test="${prodStatus.index == bidStatus.index}">
                    <tr>
                       <td width = "250px" height = "250px">
						<img src = "../../upload/${prod.picture1}">/><br>
					</td>
                        <td>
                            <strong>${prod.name}</strong><br>
                            ${prod.color}<br>
                            ${prod.size}<br>
                            ${prod.price}원
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${bid.bidResult==0}">
                                    입찰 중<br>
                                    <a href="<c:url value='/bid/editBid.do'>
                                        <c:param name='productId' value='${prod.productId}'/>
                                        </c:url>" class="changeBtn">입찰가 수정</a>
                                    <a href="<c:url value='/bid/deleteBid.do'>
                                        <c:param name='productId' value='${prod.productId}'/>
                                        </c:url>" class="deleteBtn">입찰 취소</a>
                                </c:when>
                                <c:when test="${bid.bidResult==1}">
                                    낙찰<br>
                                    <a href="<c:url value='/order/registerForm.do'>
                                        <c:param name='productId' value='${prod.productId}'/>
                                        </c:url>" class="btn">주문 하기</a>
                                </c:when>
                                <c:when test="${bid.bidResult==2}">
                                    낙찰 실패
                                </c:when>
                                <c:when test="${prod.status==0}">
                                    주문 완료
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:forEach>
    </table>
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
