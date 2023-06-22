<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>경매 상품 베스트</title>
    <style type="text/css">
        body {
            font-family: Arial, sans-serif;
        }
        
        .product-list {
	        display: flex;
	        flex-wrap: wrap;
	        justify-content: center;
	        align-items: flex-start;
	        gap: 20px;
	        margin-top: 400px; /* Add margin-top to create space between the menu bar and the product list */
	    }
        
        .product-card {
            background-color: #fff;
            border-radius: 4px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            position: relative;
            width: 250px;
        }
        
        .product-card a {
            color: #333;
            text-decoration: none;
        }
        
        .product-card img {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
        }
        
        .product-card h3 {
            font-size: 18px;
            margin-bottom: 10px;
        }
        
        .product-card p {
            font-size: 14px;
            color: #888;
            margin-bottom: 10px;
        }
        
        .product-card span {
            font-weight: bold;
            font-size: 16px;
            color: #FF4E50;
        }
        
        .product-card .rank {
            position: absolute;
            top: 10px;
            left: 10px;
            background-color: #FF4E50;
            color: #fff;
            padding: 5px 10px;
            border-radius: 4px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="product-list">
        <% int rank = 1; %>
        <c:forEach var="prod" items="${productList.pageList}">
            <div class="product-card">
                <span class="rank"><%= rank++ %></span>
                <a href="<c:url value='/auction/detail.do'>
                    <c:param name='productId' value='${prod.productId}' />
                </c:url>">
                    <img src="../../upload/${prod.picture1}" alt="Product Image">
                    <h3>${prod.name}</h3>
                    <p>
                        <c:choose>
                            <c:when test="${empty prod.price}">
                                <span>${prod.startPrice}원</span><br>
                                경매 시작가
                            </c:when>
                            <c:otherwise>
                                <span>${prod.price}원</span><br>
                                현재 최고가
                            </c:otherwise>
                        </c:choose>
                    </p>
                </a>
            </div>
        </c:forEach>
    </div>
</body>
</html>