<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<tiles:importAttribute name="title" />

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title}</title>
<link href="<c:url value="/css/screen.css"/>" rel="stylesheet"
	type="text/css" />
<link href="http://www.levi9.com/wp-content/themes/levi9/favicon.ico"
	rel="shortcut icon" type="image/x-icon" />
<script src="<c:url value="/js/lib/jquery/jquery.min.js"/>"></script>
<script src="<c:url value="/js/main.js"/>"></script>
</head>
<body>
	<header>
		<div class="top darkNoise">
			<a href="http://www.levi9.com/" target="_blank">Levi9 IT Services</a>
		</div>
		<h1>Taster</h1>
		<ul id="mainMenu">
			<li><a class="dashBoard"
				href="<c:url value="/home"/>">Dashboard</a></li>
			<li><a class="categories"
				href="<c:url value="/categories"/>">Categories</a></li>
			<li><a class="questions"
				href="<c:url value="/questions"/>">Questions</a></li>
		</ul>
	</header>
	<section id="mainContent">
		<tiles:insertAttribute name="content" />
	</section>
	<footer>
		<div class="darkNoise">&copy; 2014. Levi9 IT Services</div>
	</footer>
</body>
</html>