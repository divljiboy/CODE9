<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>Categories</h2>
<table class="dataTable">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
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
					class="button">Edit</a></td>
				<td class="highlightcolorBlack"><a
					href="<c:url value="/categories/remove/${category.id}"/>"
					class="button">Remove</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="highlightcolorBlack">
	<a href="<c:url value="/categories/new"/>" class="button">Create
		new category</a>
</div>