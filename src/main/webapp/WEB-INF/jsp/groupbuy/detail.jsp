<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>공동구매 상품 상세페이지</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
		.slider{width: 640px;height: 480px;position: relative;margin: 0 auto;
    		overflow: hidden; /* 현재 슬라이드 오른쪽에 위치한 나머지 슬라이드 들이 보이지 않도록 가림 */
		}
		.slider input[type=radio]{display: none;}
		ul.imgs{padding: 0;margin: 0;list-style: none;}
		ul.imgs li{position: absolute;left: 640px;transition-delay: 1s;
			/* 새 슬라이드가 이동해 오는 동안 이전 슬라이드 이미지가 배경이 보이도록 지연 */
			padding: 0;margin: 0;}
		.bullets{position: absolute;left: 50%;transform: translateX(-50%);bottom: 20px;z-index: 2;}
		.bullets label{display: inline-block;border-radius: 50%;background-color: rgba(0,0,0,0.55);width: 20px;
		    height: 20px;cursor: pointer;}
		/* 현재 선택된 불릿 배경 흰색으로 구분 표시 */
		.slider input[type=radio]:nth-child(1):checked~.bullets>label:nth-child(1){background-color: #fff;}
		.slider input[type=radio]:nth-child(2):checked~.bullets>label:nth-child(2){background-color: #fff;}
		.slider input[type=radio]:nth-child(3):checked~.bullets>label:nth-child(3){background-color: #fff;}
		.slider input[type=radio]:nth-child(4):checked~.bullets>label:nth-child(4){background-color: #fff;}
		.slider input[type=radio]:nth-child(1):checked~ul.imgs>li:nth-child(1){left: 0;transition: 0.5s;z-index:1;}
		.slider input[type=radio]:nth-child(2):checked~ul.imgs>li:nth-child(2){left: 0;transition: 0.5s;z-index:1;}
		.slider input[type=radio]:nth-child(3):checked~ul.imgs>li:nth-child(3){left: 0;transition: 0.5s;z-index:1;}
		.slider input[type=radio]:nth-child(4):checked~ul.imgs>li:nth-child(4){left: 0;transition: 0.5s;z-index:1;}
		
		.info{float:left;padding:10px}
		a{display:block}
	 	a:link{text-decoration:none;color:black}
		a:visited{text-decoration:none;color:black}
		a:active{text-decoration:none;color:black}
		a:hover{text-decoration:none;color:black}
	</style>
</head>
<body>
<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	
	<div class = "layout">
		<!-- 상품 메인 사진 및 상세정보 -->
		<div>
			<div class = "info">
				<table>
					<tr>
						<th>이미지경로</th>
						<td>
							${product.picture1}<br>
							${product.picture2}<br>
							<c:if test="${!empty product.picture3}" >
								${product.picture3}<br>
							</c:if>
							<c:if test="${!empty product.picture4}" >
								${product.picture4}<br>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>상품명</th>
						<td>&lt; &gt; ${product.name}</td>
					</tr>
					<tr>
						<td>사이즈</td>
						<td>${product.size}</td>
					</tr>
					<tr>
						<td>색상</td>
						<td>${product.color}<br></td>
					</tr>
					<tr>
						<td>가격</td>
						<td>${product.price}</td>
					</tr>
					<tr>
						<td>필요참가인원</td>
						<td>${product.peopleNum}</td>
					</tr>
					<tr>
						<td colspan = "2">
							<a href = "<c:url value='/closet/groupbuy.do'>
									<c:param name = 'productId' value='${product.productId}' />
									</c:url>">공동구매 참가하기</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>