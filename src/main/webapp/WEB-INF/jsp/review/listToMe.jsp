<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 리스트</title>
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

        .changeBtn, .deleteBtn, .orderBtn {
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
            background-color: #fff9a6;
        }
        .deleteBtn:hover {
            background-color: #C24E4E;
        }
		.orderBtn:hover{
            background-color: #99cd89;
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
    <!-- 메뉴바 -->
    <jsp:include page="../menu.jsp" />
    <hr>

    <div class="container">
        <h1>리뷰 리스트 조회</h1>
        <c:choose>
            <c:when test="${empty reviewList.getSource()}">
                <p class="no-data">받은 리뷰가 없습니다.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>상품 이미지</th>
                        <th>별점</th>
                        <th>내용</th>
                    </tr>
                    <c:forEach items="${reviewList.pageList}" var="review" varStatus="reviewStatus">
                        <c:forEach items="${productList.pageList}" var="prod" varStatus="prodStatus">
                            <c:if test="${reviewStatus.index == prodStatus.index}">
                                <tr>
                                    <td width="250px" height="250px">
                                    	<c:if test="${prod.DTYPE == 'Auction'}">
	                                    	<a href="<c:url value='/auction/detail.do'>
	                                			<c:param name='productId' value='${prod.productId}' />
	                            				</c:url>">
	                                        	<img src="../../upload/${prod.picture1}" class="product-image" />
	                                        </a>
                                        </c:if>
                                        <c:if test="${prod.DTYPE == 'Groupbuy'}">
	                                    	<a href="<c:url value='/groupbuy/detail.do'>
	                                			<c:param name='productId' value='${prod.productId}' />
	                            				</c:url>">
	                                        	<img src="../../upload/${prod.picture1}" class="product-image" />
	                                        </a>
                                        </c:if>
                                    </td>
                                    <td>
                                    	${review.rating}
                                    </td>
                                    <td>
   										 <div class="btn-group">
       									 <fmt:formatDate value="${review.writeDate}" pattern="yyyy-MM-dd" /><br>
      									  ${review.content}
   									 </div>
									</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
   
    <div class="page-buttons">
            <!-- 이전 페이지 버튼 -->
            <c:if test="${productList.page > 0}">
                <form action="/review/list.do2?pageName=previous" method="get" style="display: inline;">
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
                <form action="/review/list.do2?pageName=next" method="get" style="display: inline;">
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
