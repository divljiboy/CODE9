<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<tiles:importAttribute name="title" />

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><fmt:message key="${title}" /></title>
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
			<a href="<c:url value="j_spring_security_logout" />"> <fmt:message
					key="common.logout" /></a><a href="#"><fmt:message
					key="common.loggedin" /> <sec:authentication
					property="principal.username" /></a> <a
				href="<c:url value="/home?lang=sr" />"><fmt:message
					key="common.header.language.sr" /></a> <a
				href="<c:url value="/home?lang=en" />"><fmt:message
					key="common.header.language.en" /></a>
		</div>
		<h1>
			<fmt:message key="common.header.title" />
		</h1>
		<ul id="mainMenu">
			<li><a class="dashBoard" href="<c:url value="/home"/>"><fmt:message
						key="common.menu.dashboard" /></a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a class="categories" href="<c:url value="/categories"/>"><fmt:message
							key="common.menu.categories" /></a></li>
				<li><a class="questions" href="<c:url value="/questions"/>"><fmt:message
							key="common.menu.questions" /></a></li>
			</sec:authorize>
			<li><a class="testTemplates" href="<c:url value="/tests"/>"><fmt:message
						key="common.menu.tests" /></a></li>
		</ul>
	</header>
	<section id="mainContent">
		<tiles:insertAttribute name="content" />
	</section>
	<footer>
		<div class="darkNoise">
			<fmt:message key="common.footer.copyright" />
		</div>
	</footer>
</body>
</html>