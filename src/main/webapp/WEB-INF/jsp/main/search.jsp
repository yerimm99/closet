<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
    <style>
    
        .search-form {
            text-align: center;
            margin-top: 100px;
        }
        .search-form input[type="text"] {
            width: 300px;
            padding: 10px;
            font-size: 16px;
        }
        .search-form input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #337ab7;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        
        .search-results {
            margin-top: 50px;
            text-align: center;
        }
        .search-results ul {
            list-style-type: none;
            padding: 0;
        }
        .search-results li {
            font-size: 18px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="search-form">
        <h1>Search Page</h1>
        <form action="searchProcess.jsp" method="POST">
            <input type="text" name="query" placeholder="Enter your search query" required>
            <br><br>
            <input type="submit" value="Search">
        </form>
    </div>
    
    <div class="search-results">
        <h2>Search Results</h2>
        <ul>
            <%-- 검색 결과를 동적으로 생성할 경우 아래 코드를 반복문 등을 사용하여 처리할 수 있습니다. --%>
            <li>Result 1</li>
            <li>Result 2</li>
            <li>Result 3</li>
        </ul>
    </div>
</body>
</html>
