<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공동구매 상품 리스트</title>
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
	        margin-top: 500px; /* Add margin-top to create space between the menu bar and the product list */
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
                            <a href="<c:url value='/groupbuy/detail.do'>
                                <c:param name='productId' value='${prod.productId}' />
                            </c:url>">
                                <img src="../../upload/${prod.picture1}" alt="Product Image">
                                <h3>${prod.name}</h3>
                                <p> 
                                <br>${prod.price}원<br> 모집인원: ${prod.peopleSum} / ${prod.peopleNum}
                                </p>
                            </a>
                        </div>
                    </c:forEach>
        </div>
	<!-- 이전 페이지 버튼 -->
    <c:if test="${productList.page > 0}">
        <form action="/closet/groupbuy2.do?pageName=previous" method="get">
            <input type="hidden" name="pageName" value="previous">
            <input type="submit" value="Previous">
        </form>
    </c:if>
    
    ${productList.page + 1}

    <!-- 다음 페이지 버튼 -->
    <c:if test="${productList.page + 1 < productList.pageCount}">
        <form action="/closet/groupbuy2.do?pageName=next" method="get">
            <input type="hidden" name="pageName" value="next">
            <input type="submit" value="Next">
        </form>
    </c:if>
</body>
</html>