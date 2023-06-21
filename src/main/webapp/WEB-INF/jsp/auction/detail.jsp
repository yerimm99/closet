<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>경매 상품 상세페이지</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
		.slider{float:left;width: 480px;height:360px;position: relative;margin: 0 auto;
    		border:1px solid black;overflow: hidden;margin:50px 20px 0px 40px /* 현재 슬라이드 오른쪽에 위치한 나머지 슬라이드 들이 보이지 않도록 가림 */
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
		
		table{width:480px;height:360px;}
		.info{float:left;padding:20px;margin:20px 40px 0px 20px;}
		th{text-align:left;padding:2px 10px;width:170px}
		td{padding:0px 10px}
		.gobtn{text-align:center;font-size:20px;border-radius:10px;background-color:#FF3366;
		border:1px solid;width:100px;height:35px;color:white;margin-top:10px}
		.gobtn2{text-align:center;font-size:20px;border-radius:10px;
		border:1px solid black;width:100px;height:35px;color:white;margin-top:10px}
		a{display:block}
	 	a:link{text-decoration:none;color:black}
		a:visited{text-decoration:none;color:black}
		a:active{text-decoration:none;color:black}
		a:hover{text-decoration:none;color:black;}
		
		.gobtn3{text-align:center;font-size:20px;border-radius:10px;background-color:lightpink;
		border:1px solid;width:100px;height:35px;color:white;margin-top:10px}
		.dis{border:none;background-color:lightpink;height:35px;line-text:lightpink;color:white;font-size:20px;}
	</style>
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>

	<div class = "layout">
		<div style="text-align:center;font-size:24px;margin-top:60px">상품 상세보기<br><br></div>
		<!-- 상품 메인 사진 -->
			<div class="slider">
				<input type="radio" name="slide" id="slide1" checked>
				<input type="radio" name="slide" id="slide2">
				<c:if test="${!empty product.picture3}" >
        			<input type="radio" name="slide" id="slide3">
      			</c:if>
      			<c:if test="${!empty product.picture4}" >
        			<input type="radio" name="slide" id="slide4">
      			</c:if>
      			
				<ul id="imgholder" class="imgs">
					<li><img src = "../../upload/${product.picture1}"></li>
					<li><img src= "../../upload/${product.picture2}"></li>
					<c:if test="${!empty product.picture3}" >
						<li><img src= "../../upload/${product.picture3}"></li>
					</c:if>
					<c:if test="${!empty product.picture4}" >
						<li><img src="../../upload/${product.picture4}"></li>
					</c:if>
				</ul>
				<div class="bullets">
					<label for="slide1">&nbsp;</label>
					<label for="slide2">&nbsp;</label>
					<c:if test="${!empty product.picture3}" >
						<label for="slide3">&nbsp;</label>
					</c:if>
					<c:if test="${!empty product.picture4}" >
						<label for="slide4">&nbsp;</label>
					</c:if>
				</div>
			</div>
			<!-- 상품 상세정보 및 버튼 -->
			<div class = "info">
				<table>
					<tr>
						<th>상품명</th>
						<td colspan = "2">${product.name}</td>
					</tr>
					<tr>
						<th>상품정보</th>
						<td colspan = "2"><%-- &lt;<c:choose>
									<c:when test="${product.used==0}">새상품</c:when>
									<c:otherwise>중고상품</c:otherwise>
								</c:choose>
							&gt; --%>${product.description}</td>
					</tr>
					<tr>
						<th>사이즈</th>
						<td colspan = "2">${product.size}</td>
					</tr>
					<tr>
						<th>색상</th>
						<td colspan = "2">${product.color}<br></td>
					</tr>
					<tr>
						<th>상품최소가</th>
						<td colspan = "2">${product.startPrice}</td>
					</tr>
					<c:if test="${!empty product.price}" >
						<tr>
							<th>현재최고가</th>
							<td colspan = "2">${product.price}</td>
						</tr>
					</c:if>
					<tr>
						<th>마감 날짜</th>
						<td colspan = "2">${product.endDate}</td>
					</tr>
					<tr>
						<th>판매자</th>
						<td colspan = "2">${product.account.userId}</td>
					</tr>
					<tr>
						<c:if test="${product.account.userId ne supp}" >
							<td colspan = "3" class = "gobtn">
								<a href = "<c:url value='/bid/newBid.do'>
										<c:param name = 'productId' value='${product.productId}' />
										</c:url>"><b style = "color:white;font-size:18px">경매 참가하기</b>
								</a>
							</td>
						</c:if>
						<c:if test="${product.account.userId eq supp}" >
							<td class = "gobtn" style="width:33%">
								<a href="<c:url value='/auction/update.do'>
	                                            <c:param name='productId' value='${product.productId}' />
	                                        </c:url>"><b style = "color:white;font-size:18px">수정하기</b></a>
							</td>
							<c:choose>
								<c:when test="${!empty product.price}">
									<td class = "gobtn3" style="width:33%">
										<button disabled class = "dis"><b style = "color:white;font-size:18px">삭제하기</b></button>
									</td>
									<td class = "gobtn" style="width:33%">
										<a href="<c:url value='/auction/delete.do'>
	                                            <c:param name='productId' value='${product.productId}' />
	                                        </c:url>"><b style = "color:white;font-size:18px">낙찰하기</b></a>
	                                </td>
								</c:when>
								<c:otherwise>
									<td class = "gobtn" style="width:33%">
										<a href="<c:url value='/auction/delete.do'>
	                                            <c:param name='productId' value='${product.productId}' />
	                                        </c:url>"><b style = "color:white;font-size:18px">삭제하기</b></a>
	                                </td>
	                                <td class = "gobtn3" style="width:33%">
	                                	<button disabled class = "dis"><b style = "color:white;font-size:18px">낙찰하기</b></button>
	                                </td>
								</c:otherwise>
							</c:choose>
						</c:if>
					</tr>
					<tr>
						<td colspan = "3" class = "gobtn2">
							<a href = "<c:url value='/bookmark/create.do'>
									<c:param name = 'productId' value='${product.productId}' />
									</c:url>"><b style = "font-size:18px">관심상품</b>
							</a>
						</td>
					</tr>
				</table>
			</div>
	</div>
</body>
</html>