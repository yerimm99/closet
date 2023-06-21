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
        
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .product-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            grid-gap: 20px;
        }
        
        .product-card {
            background-color: #fff;
            border-radius: 4px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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
        .page-button {
		  border: none;
		  background: none;
		  color: inherit;
		  cursor: pointer;
		  padding: 0;
		}

        .page-buttons {
            text-align: center;
            margin-top: 20px;
        }
        
        .page-buttons button {
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 5px 10px;
            margin: 0 5px;
            cursor: pointer;
            font-family: Arial, sans-serif;
            font-size: 14px;
            color: #333;
            text-decoration: none;
        }
        
        .page-buttons button:hover {
            background-color: #f5f5f5;
        }
        
        .page-buttons .current {
            color: #333;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="product-list">
            <c:choose>
                <c:when test="${productList.getSource() == null}">
                    <div class="sell">
                        공동구매 상품이 하나도 없습니다.
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="prod" items="${productList.pageList}">
                        <div class="product-card">
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
                </c:otherwise>
            </c:choose>
        </div>
    </div>
	
        <div class="page-buttons">
            <!-- 이전 페이지 버튼 -->
            <c:if test="${productList.page > 0}">
                <form action="/closet/groupbuy2.do?pageName=previous" method="get" style="display: inline;">
                    <input type="hidden" name="pageName" value="previous">
                    <button type="submit">
                        &lt;
                    </button>
                </form>
            </c:if>

            <c:forEach var="pageNum" begin="1" end="${productList.pageCount}">
			    <c:choose>
			        <c:when test="${pageNum == productList.page + 1}">
			            <span class="current">${pageNum}</span>
			        </c:when>
			        <c:otherwise>
			              ${pageNum}
			        </c:otherwise>
			    </c:choose>
			</c:forEach>
			

            <!-- 다음 페이지 버튼 -->
            <c:if test="${productList.page + 1 < productList.pageCount}">
                <form action="/closet/groupbuy2.do?pageName=next" method="get" style="display: inline;">
                    <input type="hidden" name="pageName" value="next">
                    <button type="submit">
                        &gt;
                    </button>
                </form>
            </c:if>
        </div>
</body>
</html>