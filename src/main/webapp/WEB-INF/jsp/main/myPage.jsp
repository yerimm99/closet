<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>마이페이지</title>
	<style type = "text/css">
		body{margin:0}
		.layout{margin:0px auto;width:1180px;padding:10px}
		.page{text-align:center;font-size:24px;margin-top:60px;margin-bottom:30px}
		.accountInfo{border:1px solid gray;margin-right:30px;margin-bottom:60px}
		table{width:100%;height:140px;padding:10px}
		.img{width:100px; height:100px;margin:20px}
		.name{padding-bottom:10px}
		.updatebtn{text-align:center;border-radius:10px;border:1px solid black;color:white}
		.all{margin-right:30px;}
		.auction{float:left;width:45%;margin:10px;border:1px solid gray;}
		.groupbuy{float:right;width:45%;margin:10px;border:1px solid gray;}
		.clickImg{float:left}
		.func{height:40px;line-height:40px;margin-bottom:10px}
		.review{float:left;width:45%;margin:10px;border:1px solid gray;margin-top:50px}
		.like{float:right;width:45%;margin:10px;border:1px solid gray;margin-top:50px}
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
		<div class = "page">마이페이지</div>
		<!-- 개인정보 div -->
		<div class = "accountInfo">
			<table>
				<tr>
					<td rowspan = "4" width = "200"><img border="0" src="../../images/welcome.png" class = "img"/></td>
					<td colspan = "3" class = "name"><b>${account.username}님</b></td>
				</tr>
				<tr>
					<td width = "170">전화번호</td>
					<td>${account.phone}</td>
					<td></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${account.email}</td>
					<td width = "150" class = "updatebtn">
						<a href = "<c:url value='/account/updateForm.do'>
							<c:param name = 'userId' value='${account.userId}' />
							</c:url>">회원정보 수정
						</a>
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${account.address}</td>
					<td></td>
				</tr>
			</table>
		</div>
		<!-- 여러 기능 div -->
		<div class = "all">
			<div class = "auction">
				<div style = "margin:10px"><b>경매관리</b></div>
				<hr>
				<div class = "func">
					<a href="<c:url value='/auction/newAuction.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>상품등록</span>
					</a>
				</div>
				<div class = "func">
					<a href="<c:url value='/myPage/sellAuction.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>판매 내역 조회(+수정/삭제)</span>
					</a>
				</div>
				<div class = "func">
					<a href="<c:url value='/myPage/buyAuction.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>구매 내역 조회(+현황 조회)</span>
					</a>
				</div>
			</div>
			
			<div class = "groupbuy">
				<div style = "margin:10px"><b>공동구매 관리</b></div>
				<hr>
				<div class = "func">
					<a href="<c:url value='/groupbuy/registerForm.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>상품등록</span>
					</a>
				</div>
				<div class = "func">
					<a href="<c:url value='/myPage/sellGroupbuy.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>판매 내역 조회(+수정/삭제)</span>
					</a>
				</div>
				<div class = "func">
					<a href="<c:url value='/myPage/buyGroupbuy.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>구매 내역 조회(+현황 조회) </span>
					</a>
				</div>
			</div>
			
			<div class = "review">
				<div style = "margin:10px"><b>사용자리뷰관리</b></div>
				<hr>
				<div class = "func">
					<a href="<c:url value='/review/.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>리뷰작성</span>
					</a>
				</div>
				<div class = "func">
					<a href="<c:url value='/review/.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>내가 받은 리뷰 리스트</span>
					</a>
				</div>
			</div>
			
			<div class = "like">
				<div style = "margin:10px"><b>관심 상품 관리</b></div>
				<hr>
				<div class = "func">
					<a href="<c:url value='/bookmark/.do'></c:url>">
						<img border="0" src="../../images/click.png" class = "clickImg"
							width = "40" height = "40"/>
						<span>관심 상품 조회</span>
					</a>
				</div>
			</div>
			<br><br><br><br><br>
		</div>
	</div>
</body>
</html>