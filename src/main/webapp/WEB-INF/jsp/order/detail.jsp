<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>주문 확인 페이지</title>
  <style>
    /* KREAM 컬러 팔레트 */
    :root {
      --kream-primary-color: #FF3366;
      --kream-secondary-color: #333333;
      --kream-tertiary-color: #F5F5F5;
    }

    body {
      margin: 0;
      font-family: Arial, sans-serif;
      background-color: var(--kream-tertiary-color);
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    h3 {
      font-size: 24px;
      margin-bottom: 20px;
      color: var(--kream-secondary-color);
      text-align: center;
    }

    .title-container {
      display: flex;
      align-items: center;
      justify-content: center;
      margin-bottom: 10px;
    }

    .title {
      padding: 10px;
      border: 1px solid #ccc;
      width: 150px;
      margin-right: 10px;
      text-align: center;
      font-weight: bold;
      font-size: 18px;
    }

    .title:focus {
      outline: none;
      border-color: var(--kream-primary-color);
      box-shadow: 0 0 0 2px rgba(255, 51, 102, 0.5);
    }

    .goBtn {
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 10px 20px;
      background-color: var(--kream-primary-color);
      color: #fff;
      border: none;
      cursor: pointer;
      border-radius: 10px;
      margin-top: 10px;
    }

    table {
      margin: 0 auto;
    }

    table th, table td {
      text-align: center;
      padding: 10px;
    }

  </style>
</head>
<body>
  <!-- 메뉴바 -->
  <jsp:include page="../menu.jsp"/>
  <hr>
  <div class="container">
    <!-- 상품 상세정보 및 버튼 -->
    <table>
      <div class="title-container">
        <div class="title">주문 확인</div>
      </div>
      <br><br>
      <tr>
        <th>주문 번호</th>
        <td>${order.orderId}</td>
      </tr>
      <tr>
        <th>주문 날짜</th>
        <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></td>
      </tr>
      <tr>
        <th>주소</th>
        <td>${order.shipAddress}</td>
      </tr>
      <tr>
        <th>받는 사람</th>
        <td>${order.shipToName}<br></td>
      </tr>
      <tr>
        <th>가격</th>
        <td>${order.price}</td>
      </tr>
      <tr>
        <th>카드 타입</th>
        <td>${order.cardType}</td>
      </tr>
    </table>
    <br><br><br><br>
    <div class="title-container">
      <a href="<c:url value='/closet/mypage.do' />" class="goBtn">마이페이지로 돌아가기</a>
    </div>
  </div>
</body>
</html>
