<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>경매 판매 내역 조회</title>
    <style type="text/css">
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
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
            width: 100%;
            background-color: #fff;
            border-collapse: collapse;
        }

        th,
        td {
            padding: 10px;
            text-align: left;
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
            text-align: center;
            margin-top: 10px;
        }

        .btn-group a {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
            background-color: #333;
            color: #fff;
            text-decoration: none;
        }

        .btn-group a:hover {
            background-color: #555;
        }

        .btn-group button {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
            background-color: #ccc;
            color: #999;
            border: none;
            cursor: not-allowed;
        }

        .product-info {
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
    <h1>판매 내역 조회</h1>
    <c:set value="${paging}" var="paging" />
    <c:choose>
        <c:when test="${empty productList}">
            <p class="no-data">등록된 판매 내역이 없습니다.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>상품 이미지</th>
                    <th>상품 정보</th>
                    <th>기능</th>
                </tr>
                <c:forEach items="${productList}" var="prod">
                    <tr>
                        <td><img src="<c:url value='${prod.picture1}' />" alt="${prod.name}" /></td>
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
                                    <c:when test="${empty prod.price}">
                                        <a href="<c:url value='/auction/detail.do'>
                                            <c:param name='productId' value='${prod.productId}' />
                                        </c:url>">상세보기</a>
                                        <a href="<c:url value='/auction/update.do'>
                                            <c:param name='productId' value='${prod.productId}' />
                                        </c:url>">수정하기</a>
                                        <button disabled>삭제하기</button>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="<c:url value='/auction/detail.do'>
                                            <c:param name='productId' value='${prod.productId}' />
                                        </c:url>">상세보기</a>
                                        <a href="<c:url value='/auction/update.do'>
                                            <c:param name='productId' value='${prod.productId}' />
                                        </c:url>">수정하기</a>
                                        <a href="<c:url value='/auction/delete.do'>
                                            <c:param name='productId' value='${prod.productId}' />
                                        </c:url>">삭제하기</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <!-- 페이징 처리 -->
            <div class="btn-group">
                <c:if test="${paging.hasPrevious == true}">
                    <a href="?page=${preview}">&lt;&lt; 이전</a>
                </c:if>
                <c:forEach var="pagingNumber" begin="${paging.nowPageGroupStartPage}" end="${paging.nowPageGroupEndPage}">
                    <c:choose>
                        <c:when test="${pagingNumber == paging.nowPage}">
                            <strong>${pagingNumber}</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${pagingNumber - 1}">${pagingNumber}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${paging.hasNext == true}">
                    <a href="?page=${requestScope.next}">다음 &gt;&gt;</a>
                </c:if>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
