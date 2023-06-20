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
    <c:choose>
        <c:when test="${empty productList.getSource()}">
            <p class="no-data">등록된 판매 내역이 없습니다.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>상품 이미지</th>
                    <th>상품 정보</th>
                    <th>기능</th>
                </tr>
                <c:forEach items="${productList.pageList}" var="prod">
                    <tr>
                        <td width = "250px" height = "250px">
						<img src = "../../upload/${prod.picture1}">/><br>
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
                                    <c:when test="${!empty prod.price}">
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
        </c:otherwise>
    </c:choose>
    <!-- 이전 페이지, 다음 페이지 버튼 -->
    <form action="/myPage/sellAuction2.do?pageName=previous" method="get">
        <input type="hidden" name="pageName" value="previous">
        <input type="submit" value="Previous">
    </form>

    <form action="/myPage/sellAuction2.do?pageName=next" method="get">
        <input type="hidden" name="pageName" value="next">
        <input type="submit" value="Next">
    </form>
</div>
</body>
</html>
