<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>Questions</h2>
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