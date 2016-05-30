<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Add/Edit Category</title>
</head>
<body>
	<div id="addEditCategory">
		<c:url var="action" value="/categories" />
		<form:form id="formCategory" action="${action}" method="post"
			modelAttribute="category">
			<fieldset>
				<form:hidden path="id" />
				<form:label path="name">Name</form:label>
				<form:input path="name"/>
				<form:errors path="name"/>
			</fieldset>
			<p>
				<button type="submit" name="save">Submit</button>
				<button type="submit" name="cancel">Cancel</button>
			</p>
		</form:form>
	</div>
</body>
</html>

