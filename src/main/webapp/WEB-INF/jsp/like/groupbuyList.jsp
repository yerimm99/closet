<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공동구매 관심 상품 리스트</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
		
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
       .btn {
			text-align: center;
			font-size: 20px;
			border-radius: 10px;
			border: 1px solid black;
			width: 100px;
			height: 35px;
			color: white;
			margin-top: 10px;
		}

		.btn-cancel {
		    background-color: #FF3366;
		    border-color: #FF3366;
		    font-family: Arial, sans-serif; /* 원하는 폰트 설정 */
		    font-size: 14px; /* 원하는 글씨 크기 설정 */
		    color: #FFFFFF; /* 흰색으로 설정 */
		    display: flex; /* 텍스트를 가운데로 정렬하기 위해 flex 사용 */
		    align-items: center; /* 텍스트를 수직 가운데로 정렬 */
		    justify-content: center; /* 텍스트를 수평 가운데로 정렬 */
		    height: 35px; /* 원하는 버튼 높이 설정 */
		    width: 100px; /* 원하는 버튼 너비 설정 */
		    border-radius: 10px;
		}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
	
		<div style="text-align:center;font-size:24px;margin-top:60px">공동구매 관심 상품 리스트<br><br></div>
		<div class="container">
        <div class="product-list">
           
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
                            </a><br>
                            <a href = "<c:url value='/like/delete.do'>
								<c:param name = 'productId' value='${prod.productId}' />
								</c:url>" class="btn btn-cancel">관심상품 취소
							</a>
                        </div>
                    </c:forEach>
        </div>
    </div>
	
        <div class="page-buttons">
            <!-- 이전 페이지 버튼 -->
            <c:if test="${productList.page > 0}">
                <form action="/like/groupbuyList2.do?pageName=previous" method="get" style="display: inline;">
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
                <form action="/like/groupbuyList2.do?pageName=next" method="get" style="display: inline;">
                    <input type="hidden" name="pageName" value="next">
                    <button type="submit">
                        &gt;
                    </button>
                </form>
            </c:if>
        </div>
	</div>
</body>
</html>