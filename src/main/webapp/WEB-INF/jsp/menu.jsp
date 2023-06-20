<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>메뉴바</title>
  <style type="text/css">
    .layout {
      margin: 0px auto;
      width: 1180px;
      height: 58px;
      padding: 10px;
      font-family: 'Noto Sans', Arial, sans-serif;
    }

    .logo {
      font-weight: bold;
      width: 240px;
      height: 58px;
      font-size: 40px;
      float: left;
      margin: 5px 0px 4px 0px;
      font-family: 'Pacifico', cursive;
      color: black;
    }

    .navitool {
      float: right;
      width: 600px;
      height: 40px;
      padding: 0px;
      margin: 10px 20px 10px 0px;
      font-family: 'Noto Sans', Arial, sans-serif;
    }

    .navi {
      float: left;
      width: 90px;
      padding: 10px;
      height: 20px;
      font-size: 18px;
      text-align: center;
      margin: 5px 0px 10px 0px;
    }

    .login {
      float: right;
      width: 200px;
      padding: 10px;
      height: 20px;
      font-size: 16px;
      text-align: center;
      margin: 15px 0px 10px 0px;
    }

    a {
      display: block;
      text-decoration: none;
      color: black;
    }

    a:hover {
      font-weight: bold;
    }

    .aLogin {
      margin-left: 30px;
      margin-right: 30px;
      float: left;
    }

    .logout {
      float: left;
      margin-left: 50px;
    }

    .mypage {
      float: left;
      margin-left: 20px;
    }

    .mypageImg {
      width: 22px;
      height: 22px;
    }
  </style>
  <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="layout">
    <div class="login">
      <c:choose>
        <c:when test="${empty userSession.account}">
          <a href="<c:url value='/account/SignonForm.do' />" class="aLogin">로그인</a>
          <a href="<c:url value='/account/registerForm.do' />">회원가입</a>
        </c:when>
        <c:otherwise>
          <a href="<c:url value='/closet/mypage.do' />" class="mypage">
            <img border="0" src="../../images/mypage.gif" class="mypageImg"/>
          </a>
          <a href="<c:url value='/account/signoff.do' />" class="logout">로그아웃</a>
        </c:otherwise>
      </c:choose>
    </div>
    <div class="logo"><a href="<c:url value='/closet/index.do' />">&amp;closet</a></div>
    <div class="navitool">
      <div class="navi"><a href="<c:url value='/closet/auction.do' />">경매</a></div>
      <div class="navi"><a href="<c:url value='/closet/groupbuy.do' />">공동구매</a></div>
      <div class="navi"><a href="<c:url value='/closet/best.do' />">BEST</a></div>
      <div class="navi"><a href="<c:url value='/closet/search.do' />">검색</a></div>
    </div>
  </div>
</body>
</html>
