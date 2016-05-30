<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2>Add/Edit Category</h2>
<c:url var="action" value="/categories" />
<form:form id="formCategory" action="${action}" method="post" modelAttribute="category">
	<fieldset>
		<form:hidden path="id" />
		<form:label path="name">Name</form:label>
		<form:input path="name" cssErrorClass="error" />
		<form:errors path="name" cssClass="errorMessage" />
	</fieldset>
	<div class="highlightcolorBlack">
		<button type="submit" name="save" class="button">Submit</button>
		<button type="submit" name="cancel" class="button">Cancel</button>
	</div>
</form:form>