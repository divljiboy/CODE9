<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Questions</title>
</head>
<body>
	<div id="questions">
		<c:forEach items="${questions}" var="questionVar">
			<h2>${questionVar.key}:</h2>
			<c:forEach items="${questionVar.value}" var="question">
				<div>
					<div>${question.content}</div>
					<ol>
						<c:forEach items="${question.answers}" var="answer">
							<li>${answer.content}<c:if test="${answer.correct}"> - correct</c:if></li>
						</c:forEach>
					</ol>
					<div>
						<a href="<c:url value="/questions/edit/${question.id}"/>">Edit</a>
						<a href="<c:url value="/questions/remove/${question.id}"/>">Remove</a>
					</div>
				</div>
			</c:forEach>
		</c:forEach>
	</div>
	<div id="newQuestion">
		<a href="<c:url value="/questions/new"/>">Create new question</a>
	</div>
</body>
</html>