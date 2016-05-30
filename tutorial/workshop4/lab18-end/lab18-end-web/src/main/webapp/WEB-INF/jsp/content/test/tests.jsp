<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><fmt:message key="page.tests.header" /></h2>
<table class="dataTable">
	<thead>
			<tr>
			<th><fmt:message key="common.label.id" /></th>
			<th><fmt:message key="common.label.name" /></th>
			<th><fmt:message key="common.label.createDate" /></th>
			<th><fmt:message key="common.label.createdBy" /></th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${tests}" var="test">
			<tr>
				<td><c:out value="${test.id}"/></td>
				<td><c:out value="${test.name}"/></td>
				<td><spring:eval expression="test.createDate"/></td>
				<td><c:out value="${test.createdBy}"/></td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<td class="highlightcolorBlack"><a href="<c:url value="/tests/editQuestions/${test.id}"/>" class="button"><fmt:message key="page.tests.action.addRemoveQuestions"/></a></td>
				<td class="highlightcolorBlack"><a href="<c:url value="/tests/edit/${test.id}"/>" class="button"><fmt:message key="common.action.edit" /></a></td>
				<td class="highlightcolorBlack"><a href="<c:url value="/tests/remove/${test.id}"/>" class="button"><fmt:message key="common.action.remove" /></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<div class="highlightcolorBlack"><a href="<c:url value="/tests/new"/>" class="button"><fmt:message key="page.tests.action.addNewTest" /></a></div>
</sec:authorize>
