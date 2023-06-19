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
    <c:set value="${paging}" var="paging"></c:set>
    <table>
        <tr>
            <th>상품 사진</th>
            <th>상품 정보</th>
            <th>결과</th>
        </tr>
        <c:forEach items="${productList}" var="prod" varStatus="prodStatus">
            <c:forEach items="${bidList}" var="bid" varStatus="bidStatus">
                <c:if test="${prodStatus.index == bidStatus.index}">
                    <tr>
                        <td>
                            <img src="<c:url value='${prod.picture1}'/>" class="product-image" alt="Product Image">
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
    <!-- 페이징 처리 -->
    <div class="text-center">
        <!-- 이전 페이지 그룹 있나? -->
        <c:if test="${paging.hasPrevious==true}">
            <a href="?page=${preview}"><i class="fas fa-lg fa-angle-double-left"></i></a>&nbsp;
        </c:if>
        <!-- 페이지 번호 표시 -->
        <c:forEach var="pagingNumber" begin="${paging.nowPageGroupStartPage}" end="${paging.nowPageGroupEndPage}">
            <c:choose>
                <c:when test="${pagingNumber==paging.nowPage}">
                    <strong>${pagingNumber}</strong>&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="?page=${pagingNumber-1}">${pagingNumber}</a>&nbsp;
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <!-- 다음 페이지 그룹 있나? -->
        <c:if test="${paging.hasNext==true}">
            <a href="?page=${requestScope.next}"><i class="fas fa-lg fa-angle-double-right"></i></a>
        </c:if>
    </div>
</div>
</body>
</html>
