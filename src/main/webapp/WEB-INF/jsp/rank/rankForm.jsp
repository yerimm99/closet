<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Rankings</title>
</head>
<body>
    <h1>Product Rankings</h1>
    <table>
        <thead>
            <tr>
                <th>Rank</th>
                <th>Name</th>
                <th>Views</th>
            </tr>
        </thead>
        <tbody>
            <%-- Retrieve the ranked products from the controller --%>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.rank}</td>
                    <td>${product.name}</td>
                    <td>${product.views}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>