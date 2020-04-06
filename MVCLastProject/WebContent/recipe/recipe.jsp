<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ================================== 카테고리 (Image Slide 하단, 3개짜리) ================================== -->
<div class="wrapper row2">
  <div id="services" class="clear"> 
    <div class="group">
      <div class="one_third first">
        <article class="service"><i class="icon red circle fa fa-bell-o"></i>
          <h2 class="heading" style="font-family: 맑은 고딕">한류관광</h2>
          <p class="btmspace-10">서울의 10대 한류명소<br>
                  한류스타 출연 공연 일정<br>
          K-pop & K-drama 추천코스
          </p>
          <p><a href="#">Read More &raquo;</a></p>
        </article>
      </div>
      <div class="one_third">
        <article class="service"><i class="icon orange circle fa fa-bicycle"></i>
          <h2 class="heading" style="font-family: 맑은 고딕">추천코스</h2>
          <p class="btmspace-10">서울 한 바퀴<br>
          2019 창경궁 야간 상시관람<br>
                   서울의 무인서비스
          </p>
          <p><a href="#">Read More &raquo;</a></p>
        </article>
      </div>
      <div class="one_third">
        <article class="service"><i class="icon green circle fa fa-mortar-board"></i>
          <h2 class="heading" style="font-family: 맑은 고딕">공연예약</h2>
          <p class="btmspace-10">
                   아라리오뮤지엄 인 스페이스 서울<br>
                   모던 앤 클래식 3<br>
                   난타(NANTA) - 명동공연
          </p>
          <p><a href="#">Read More &raquo;</a></p>
        </article>
      </div>
    </div>
    <div class="clear"></div>
  </div>
</div>
<!-- ================================== 최하단 '오늘의 음식점' ================================== -->
<div class="wrapper row2">
  <div class="latest"> 
    <ul class="nospace group">
    	<c:forEach var="vo" items="${rlist }">
	    	<li>
		        <figure><a class="overlay" href="#"><img src="${vo.poster }" alt=""></a>
		          <figcaption class="inspace-30 center">
		            <p class="bold uppercase">${vo.title }</p>
		            <p>${vo.chef }</p>
		            <p><a href="#">View Here &raquo;</a></p>
		          </figcaption>
		        </figure>
	     	</li>
    	</c:forEach>
    </ul>
  </div>
</div>
</body>
</html>