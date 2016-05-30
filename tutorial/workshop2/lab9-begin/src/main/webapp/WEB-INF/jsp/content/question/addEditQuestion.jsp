<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>Add/Edit Question</h2>
<div id="addEditQuestion">
	<c:url var="action" value="/questions" />
	<form:form id="formQuestion" action="${action}" method="post"
		modelAttribute="question">
		<fieldset>
			<form:hidden path="id" />
			<form:label path="content">Content</form:label>
			<form:input path="content" />
			<form:errors path="content" />
			<br />
			<form:label path="category">Category</form:label>
			<form:select path="category">
				<form:option value="-1">-- No category --</form:option>
				<form:options items="${categories}" itemLabel="name" itemValue="id" />
			</form:select>
			<br />
			<form:label path="answers">Answers</form:label>
			<form:errors path="answers" />
			<br />
			<c:if test="${fn:length(question.answers) gt 0}">
				<c:forEach begin="0" end="${fn:length(question.answers)-1}"
					varStatus="status">
					<form:hidden path="answers[${status.index}].id" />
					<form:label path="answers[${status.index}].content">${status.count}</form:label>
					<form:input path="answers[${status.index}].content" />
					<form:label path="answers[${status.index}].correct">Correct:</form:label>
					<form:checkbox path="answers[${status.index}].correct" />
					<c:if test="${status.end gt 0}">
						<button type="submit" name="removeAnswer"
							value="${question.answers[status.index].id}">Remove
							answer</button>
					</c:if>
					<form:errors path="answers[${status.index}].content" />
					<br />
				</c:forEach>
			</c:if>
			<button type="submit" name="addAnswer">Add answer</button>
		</fieldset>
		<p>
			<button type="submit" name="save">Save</button>
			<button type="submit" name="cancel">Cancel</button>
		</p>
	</form:form>
</div>