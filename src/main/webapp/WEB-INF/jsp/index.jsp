<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>메인 페이지</title>
	<style type = "text/css">
		body {
            font-family: Arial, sans-serif;
            margin: 0;
        }
		.layout{margin:0px auto;width:2320px;}
		/* 초기화 */
		#slide * {
		  margin: 0;
		  padding: 0;
		  box-sizing: border-box;
		}
		li {
		  list-style-type: none;
		}
		/* 보여줄 구간의 높이와 넓이 설정 */
		#slideShow {
		  /*전체 컨테이너*/
		  width: 100%;
		  height: 450px;
		  position: relative;
		  margin: 10px auto;
		  overflow: hidden; /*리스트 형식으로 이미지를 일렬로 정렬할 것이기 때문에, 500px 밖으로 튀어 나간 이미지들은 hidden으로 숨겨줘야됨*/
		}
		.slides {
		  /*이미지들이 담겨있는 슬라이드 컨테이너*/
		  position: absolute;
		  left: 0;
		  top: 0;
		  width: 1200px; /* 슬라이드할 사진과 마진 총 넓이 */
		  transition: 0.5s ease-out; /*ease-out: 처음에는 느렸다가 점점 빨라짐*/
		} /* 첫 번째 슬라이드 가운데에 정렬하기위해 첫번째 슬라이드만 margin-left조정 */
		.slides li:first-child {
		  margin-left: 0px;
		} /* 슬라이드들 옆으로 정렬 */
		.slides li:not(:last-child) {
		  float: left;
		  margin-right: 0px;
		}
		.controller span {
		  position: absolute;
		  background-color: transparent;
		  color: black;
		  text-align: center;
		  border-radius: 50%;
		  padding: 10px 20px;
		  top: 50%;
		  font-size: 1.3em;
		  cursor: pointer;
		} /* 이전, 다음 화살표에 마우스 커서가 올라가 있을때 */
		.controller span:hover {
		  background-color: rgba(128, 128, 128, 0.11);
		}
		.prev {
		  left: 10px;
		} /* 이전 화살표에 마우스 커서가 올라가 있을때 이전 화살표가 살짝 왼쪽으로 이동하는 효과*/
		.prev:hover {
		  transform: translateX(-10px);
		}
		.next {
		  right: 10px;
		} /* 다음 화살표에 마우스 커서가 올라가 있을때 이전 화살표가 살짝 오른쪽으로 이동하는 효과*/
		.next:hover {
		  transform: translateX(10px);
		}
		
		.name{font-size:24px;margin-top:60px;margin-bottom:30px;margin-left:100px}
		.list{margin-left:100px}
		.text-center{display:none}
	
        .product-section {
			margin-bottom: 30px;
		}

		.product-section h2 {
			font-size: 20px;
			font-weight: bold;
			margin-bottom: 10px;
		}

		.product-list {
			display: grid;
			grid-template-columns: repeat(4, 1fr);
			grid-gap: 20px;
		}

		.product-card {
			background-color: #fff;
			border-radius: 4px;
			padding: 20px;
			box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		}

		.product-card a {
			color: #333;
			text-decoration: none;
		}

		.product-card img {
			max-width: 100%;
			height: auto;
			margin-bottom: 10px;
		}

		.product-card h3 {
			font-size: 18px;
			margin-bottom: 10px;
		}

		.product-card p {
			font-size: 14px;
			color: #888;
			margin-bottom: 10px;
		}

		.product-card span {
			font-weight: bold;
			font-size: 16px;
			color: #FF4E50;
		}
	</style>
	
</head>
<body>
	<!-- 메뉴바 -->
	<jsp:include page = "menu.jsp"/>
	
	<!-- 이벤트 배너 -->
	<div id="slideShow">
	    <ul class="slides">
	      <li><img src="../../images/banner.png" alt="picture"  style="max-width: 100%; height: 450px;"></li>
	      <li><img src="../../images/banner1.png" alt="picture" style="max-width: 100%; height: 450px;"></li>
	      <li><img src="../../images/banner_2.png" alt="picture" style="max-width: 100%; height: 450px;"></li>
	      <li><img src="../../images/banner_3.png" alt="picture" style="max-width: 100%; height: 450px;"></li>
	      <li><img src="../../images/banner4.png" alt="picture" style="max-width: 100%; height: 450px;"></li>
	    </ul>
	    <p class="controller">
	      <!-- &lang: 왼쪽 방향 화살표 &rang: 오른쪽 방향 화살표 --> 
	      <span class="prev">&lang;</span> 
	      <span class="next">&rang;</span>
	    </p>
  	</div>
  	<script>
	  	let slides = document.querySelector('.slides');
	  	let slideImg = document.querySelectorAll('.slides li');
	  	currentIdx = 0;
	  	slideCount = slideImg.length;
	  	prev = document.querySelector('.prev'); //이전 버튼
	  	next = document.querySelector('.next'); //다음 버튼
	  	slideWidth = slides.clientWidth; //슬라이드이미지 넓이
	  	slideMargin = 0; //슬라이드 끼리의 마진값
	  	makeClone(); // 처음이미지와 마지막 이미지 복사 함수
	  	initfunction(); //슬라이드 넓이와 위치값 초기화 함수
	  	function makeClone() {
	  	  let cloneSlide_first = slideImg[0].cloneNode(true);
	  	  let cloneSlide_last = slides.lastElementChild.cloneNode(true);
	  	  slides.append(cloneSlide_first);
	  	  slides.insertBefore(cloneSlide_last, slides.firstElementChild);
	  	}
	  	function initfunction() {
	  	  slides.style.width = (slideWidth + slideMargin) * (slideCount + 2) + 'px';
	  	  slides.style.left = -(slideWidth + slideMargin) + 'px';
	  	}
	  	next.addEventListener('click', function () {
	  	  //다음 버튼 눌렀을때
	  	  if (currentIdx <= slideCount - 1) {
	  	    //슬라이드이동
	  	    slides.style.left = -(currentIdx + 2) * (slideWidth + slideMargin) + 'px';
	  	    slides.style.transition = `${0.5}s ease-out`; //이동 속도
	  	  }
	  	  if (currentIdx === slideCount - 1) {
	  	    //마지막 슬라이드 일때
	  	    setTimeout(function () {
	  	      //0.5초동안 복사한 첫번째 이미지에서, 진짜 첫번째 위치로 이동
	  	      slides.style.left = -(slideWidth + slideMargin) + 'px';
	  	      slides.style.transition = `${0}s ease-out`;
	  	    }, 500);
	  	    currentIdx = -1;
	  	  }
	  	  currentIdx += 1;
	  	});
	  	prev.addEventListener('click', function () {
	  	  //이전 버튼 눌렀을때
	  	  console.log(currentIdx);
	  	  if (currentIdx >= 0) {
	  	    slides.style.left = -currentIdx * (slideWidth + slideMargin) + 'px';
	  	    slides.style.transition = `${0.5}s ease-out`;
	  	  }
	  	  if (currentIdx === 0) {
	  	    setTimeout(function () {
	  	      slides.style.left = -slideCount * (slideWidth + slideMargin) + 'px';
	  	      slides.style.transition = `${0}s ease-out`;
	  	    }, 500);
	  	    currentIdx = slideCount;
	  	  }
	  	  currentIdx -= 1;
	  	});
	 // 자동으로 슬라이드 실행
	  	let intervalId; // setInterval을 담을 변수
	  	const autoSlide = () => {
	  	  intervalId = setInterval(() => {
	  	    next.click(); // 다음 버튼 클릭 이벤트 호출
	  	  }, 3000); // 3초마다 슬라이드 이동
	  	};
	  	autoSlide(); // 페이지 로드 후 자동 슬라이드 실행

	  	// 마우스가 슬라이드 위에 있을 때 슬라이드 멈추기
	  	slides.addEventListener('mouseenter', () => {
	  	  clearInterval(intervalId); // 슬라이드 멈춤
	  	});

	  	// 마우스가 슬라이드에서 벗어났을 때 슬라이드 다시 실행
	  	slides.addEventListener('mouseleave', () => {
	  	  autoSlide(); // 자동 슬라이드 실행
	  	});

  	</script>
  	<br><br><br><br>
	<div class = "layout">
		<div class="product-section">
            <h2><a href="<c:url value='/closet/auction.do' />">최신 경매 상품</a></h2>
            <div class="product-list">
                <c:forEach var="prod" items="${AuctionList.pageList}">
                    <div class="product-card">
                        <a href="<c:url value='/auction/detail.do'><c:param name='productId' value='${prod.productId}' /></c:url>">
                            <img src="../../upload/${prod.picture1}" alt="Product Image">
                            <h3>${prod.name}</h3>
                            <p>
                                <c:choose>
                                    <c:when test="${empty prod.price}">
                                        <span>${prod.startPrice}원</span><br>
                                        경매 시작가
                                    </c:when>
                                    <c:otherwise>
                                        <span>${prod.price}원</span><br>
                                        현재 최고가
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
        <br><br><br>
        <div class="product-section">
            <h2><a href="<c:url value='/closet/groupbuy.do' />">공동구매 상품</a></h2>
            <div class="product-list">
                <c:forEach var="gprod" items="${GroupbuyList.pageList}">
                    <div class="product-card">
                        <a href="<c:url value='/groupbuy/detail.do'><c:param name='productId' value='${gprod.productId}' /></c:url>">
                            <img src="../../upload/${gprod.picture1}" alt="Product Image">
                            <h3>${gprod.name}</h3>
                            <p> 
                                <br>${gprod.price}원<br> 모집인원: ${gprod.peopleSum} / ${gprod.peopleNum}
                            </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
	</div>
</body>
</html>