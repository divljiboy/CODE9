<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>Add/Edit Category</h2>
<div id="addEditCategory">
	<c:url var="action" value="/categories" />
	<form:form id="formCategory" action="${action}" method="post"
		modelAttribute="category">
		<fieldset>
			<form:hidden path="id" />
			<form:label path="name">Name</form:label>
			<form:input path="name" />
			<form:errors path="name"/>
		</fieldset>
		<p>
			<button type="submit" name="save">Submit</button>
			<button type="submit" name="cancel">Cancel</button>
		</p>
	</form:form>
</div>