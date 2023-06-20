<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>&Closet Search</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
        }
        
        .search-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 50px;
        }
        
        .search-form {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
        }
        
        .search-form input[type="text"] {
            width: 500px;
            padding: 10px;
            font-size: 20px;
            border: none;
            border-radius: 4px;
            border-bottom: 1px solid #ddd; /* 밑줄 추가 */
        }
        
        .search-form input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 10px;
        }
        
        .search-recommendations {
            text-align: left;
            margin-bottom: 20px;
            margin-right: 190px;
        }
        
        .search-recommendations ul {
            list-style-type: none;
            padding: 0;
        }
        
        .search-recommendations li {
            display: inline-block;
            margin: 5px;
            padding: 5px 10px;
            background-color: #E2E2E2;
            color: #7C7876;
            border-radius: 20px;
            cursor: pointer;
        }
        
    </style>
</head>
<body>
<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
    <div class="search-container">
        <form class="search-form" action="<c:url value='/closet/searchResult.do'/>" method="POST">
            <input type="text" name="keyword" placeholder="상품명" required>
            <input type="submit" value="Search">
        </form>
        <div class="search-recommendations">
            <b>추천 검색어</b>
            <ul>
                <li>나이키</li>
                <li>아디다스</li>
                <li>빈폴</li>
                <li>디젤</li>
                <li>스투시</li>
                <li>반팔</li>
            </ul>
        </div>

    </div>
</body>
</html>
