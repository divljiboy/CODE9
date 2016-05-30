<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>
	<fmt:message key="page.questions.header" />
</h2>
<c:forEach items="${questions}" var="questionVar">
	<h2>
		<c:choose>
			<c:when test="${questionVar.key != 'Uncategorized'}">
				${questionVar.key}:
			</c:when>
			<c:otherwise>
				<fmt:message key="page.questions.uncategorized" />
		</c:otherwise>
		</c:choose>
	</h2>
	<c:forEach items="${questionVar.value}" var="question">
		<div>
			<div>${question.content}</div>
			<ol>
				<c:forEach items="${question.answers}" var="answer">
					<li>${answer.content}<c:if test="${answer.correct}">
							<fmt:message key="page.questions.label.correct" />
						</c:if></li>
				</c:forEach>
			</ol>
			<div class="highlightcolorBlack">
				<a href="<c:url value="/questions/edit/${question.id}"/>"
					class="button"><fmt:message key="common.action.edit" /></a> <a
					href="<c:url value="/questions/remove/${question.id}"/>"
					class="button"><fmt:message key="common.action.remove" /></a>
			</div>
		</div>
	</c:forEach>
</c:forEach>
<div class="highlightcolorBlack">
	<a href="<c:url value="/questions/new"/>" class="button"><fmt:message
			key="page.questions.action.addNewQuestion" /></a>
</div>