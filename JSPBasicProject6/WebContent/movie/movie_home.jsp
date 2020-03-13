<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- Container (Portfolio Section) -->
	<div id="portfolio" class="container-fluid text-center bg-grey">
		<h2>Portfolio</h2>
		<br>
		<h4>What we have created</h4>
		<div class="row text-center slideanim">
			<div class="col-sm-4">
				<div class="thumbnail">
					<img src="https://www.w3schools.com/bootstrap/paris.jpg" alt="Paris" width="400" height="300">
					<p>
						<strong>Paris</strong>
					</p>
					<p>Yes, we built Paris</p>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<img src="https://www.w3schools.com/bootstrap/newyork.jpg" alt="New York" width="400" height="300">
					<p>
						<strong>New York</strong>
					</p>
					<p>We built New York</p>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="thumbnail">
					<img src="https://www.w3schools.com/bootstrap/sanfran.jpg" alt="San Francisco" width="400" height="300">
					<p>
						<strong>San Francisco</strong>
					</p>
					<p>Yes, San Fran is ours</p>
				</div>
			</div>
		</div>
		<br>

		<h2>What our customers say</h2>
		<div id="myCarousel" class="carousel slide text-center"
			data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
				<li data-target="#myCarousel" data-slide-to="4"></li>
				<li data-target="#myCarousel" data-slide-to="5"></li>
				<li data-target="#myCarousel" data-slide-to="6"></li>
				<li data-target="#myCarousel" data-slide-to="7"></li>
				<li data-target="#myCarousel" data-slide-to="8"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<h4>
						"This company is the best. I am so happy with the result!"<br>
						<span>Michael Roe, Vice President, Comment Box</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"One word... WOW!!"<br>
						<span>John Doe, Salesman, Rep Inc</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"Could I... BE any more happy with this company?"<br>
						<span>Chandler Bing, Actor, FriendsAlot</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"asdgqaweg"<br>
						<span>Chandler Bing, Actor, FriendsAlot</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"qwegqwgeqwegqegq"<br>
						<span>Chandler Bing, Actor, FriendsAlot</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"qwegqwerqwea"<br>
						<span>Chandler Bing, Actor, FriendsAlot</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"qerhqerh"<br>
						<span>Chandler Bing, Actor, FriendsAlot</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"qerhqerq"<br>
						<span>Chandler Bing, Actor, FriendsAlot</span>
					</h4>
				</div>
				<div class="item">
					<h4>
						"hwerhwerhwserhwq"<br>
						<span>Chandler Bing, Actor, FriendsAlot</span>
					</h4>
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>

</body>
</html>