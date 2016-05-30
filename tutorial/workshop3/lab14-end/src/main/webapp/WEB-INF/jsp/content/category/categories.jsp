<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>
	<fmt:message key="page.categories.header" />
</h2>
<table class="dataTable">
	<thead>
		<tr>
			<th><fmt:message key="common.label.id" /></th>
			<th><fmt:message key="common.label.name" /></th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${categories}" var="category">
			<tr>
				<td><c:out value="${category.id}" /></td>
				<td><c:out value="${category.name}" /></td>
				<td class="highlightcolorBlack"><a
					href="<c:url value="/categories/edit/${category.id}"/>"
					class="button"><fmt:message key="common.action.edit" /></a></td>
				<td class="highlightcolorBlack"><a
					href="<c:url value="/categories/remove/${category.id}"/>"
					class="button"><fmt:message key="common.action.remove" /></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="highlightcolorBlack">
	<a href="<c:url value="/categories/new"/>" class="button"><fmt:message
			key="page.categories.action.addNewCategory" /></a>

</div>
