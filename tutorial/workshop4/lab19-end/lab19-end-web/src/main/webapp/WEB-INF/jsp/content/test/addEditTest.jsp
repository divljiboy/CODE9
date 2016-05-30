<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><fmt:message key="page.addEditTest.header" /></h2>
<c:url var="action" value="/tests" />
<form:form id="formTest" action="${action}" method="post" modelAttribute="testDTO">
	<fieldset>
		<form:hidden path="id" />
		<form:label path="name"><fmt:message key="common.label.name" /></form:label>
		<form:input path="name" cssErrorClass="error" />
		<form:errors path="name" cssClass="errorMessage" />
		<br />
		<form:label path="createDate"><fmt:message key="common.label.createDate" /></form:label>
		<form:input path="createDate" readonly="true" tabindex="-1" cssErrorClass="error" />
		<form:errors path="createDate" cssClass="errorMessage" />
		<br />
		<form:label path="createdBy"><fmt:message key="common.label.createdBy" /></form:label>
		<form:input path="createdBy" cssErrorClass="error" />
		<form:errors path="createdBy" cssClass="errorMessage" />
	</fieldset>
	<div class="highlightcolorBlack">
		<button type="submit" name="save" class="button"><fmt:message key="common.action.save" /></button>
		<button type="submit" name="cancel" class="button"><fmt:message key="common.action.cancel" /></button>
	</div>
</form:form>
