<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>best 페이지</title>
    <style type="text/css">
        body {
            margin: 0;
        }
        
        .layout {
            margin: 0px auto;
            width: 1180px;
            padding: 10px;
        }
        
        .sell {
            background-color: white;
            width: 100px;
            height: 40px;
            font-size: 24px;
            text-align: center;
            padding: 10px;
            float: left;
        }
        .category {
   			 background-color: white;
		    height: 60px;
		    margin-left: 250px;
		    display: flex;
		    justify-content: flex-start;
		    align-items: center;
		    padding-left: 20px;
		}
		
		.category a {
		    background-color: white;
		    height: 40px;
		    font-size: 18px;
		    text-align: center;
		    margin-right: 15px;
		    text-decoration-color: gray;
		    padding: 8px 15px;
		    border: 1px solid gray;
		    border-radius: 20px;
		    color: black;
		    transition: background-color 0.3s ease;
		}
		
		.category a:hover {
		    background-color: lightgray;
		}
		
		.category a.active {
		    background-color: lightgray;
		    font-weight: bold;
		}

        
        .list {
            background-color: white;
            height: 60px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 80px;
            height: 600px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        
        .notNew {
            margin-top: 5px;
        }
        
        a {
            display: block;
        }
        
        a:link {
            text-decoration: none;
            color: black;
        }
        
        a:visited {
            text-decoration: none;
            color: black;
        }
        
        a:active {
            text-decoration: none;
            color: black;
        }
        
        a:hover {
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>
    <!-- 메뉴바 -->
    <jsp:include page="../menu.jsp" />
    <hr>
    
    <div class="layout">
        <br>
        <div class="category">
            <a href="<c:url value='/rank/auction/likeList.do' />">경매 좋아요순</a>
            <a href="<c:url value='/rank/auction/reviewList.do' />">경매 리뷰순</a>
            <a href="<c:url value='/rank/groupbuy/likeList.do' />">공동구매 좋아요순</a>
            <a href="<c:url value='/rank/groupbuy/reviewList.do' />">공동구매 리뷰순</a>
        </div>
        
        <!-- 해당 상품 리스트 -->
        <div class="list">
            <c:if test="${DTYPE eq 'Auction'}">
                <jsp:include page="../rank/auctionList.jsp" />
            </c:if>
            <c:if test="${DTYPE eq 'Groupbuy'}">
                <jsp:include page="../rank/groupbuyList.jsp" />
            </c:if>
        </div>
    </div>
</body>
</html>
