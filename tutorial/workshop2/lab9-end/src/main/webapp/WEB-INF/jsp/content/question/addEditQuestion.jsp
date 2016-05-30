<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>Add/Edit Question</h2>
<c:url var="action" value="/questions" />
<form:form id="formQuestion" action="${action}" method="post"
	modelAttribute="question">
	<fieldset>
		<form:hidden path="id" />
		<form:label path="content">Content</form:label>
		<form:input path="content" cssErrorClass="error" />
		<form:errors path="content" cssClass="errorMessage" />
		<br />
		<form:label path="category">Category</form:label>
		<form:select path="category">
			<form:option value="-1">-- No category --</form:option>
			<form:options items="${categories}" itemLabel="name" itemValue="id" />
		</form:select>
		<br />
		<form:label path="answers">Answers</form:label>
		<form:errors path="answers" cssClass="errorMessage" />
		<br />
		<c:if test="${fn:length(question.answers) gt 0}">
			<c:forEach begin="0" end="${fn:length(question.answers)-1}"
				varStatus="status">
				<form:hidden path="answers[${status.index}].id" />
				<form:input path="answers[${status.index}].content" cssErrorClass="error" />
				<form:label path="answers[${status.index}].correct">Correct:</form:label>
				<form:checkbox path="answers[${status.index}].correct" />
				<c:if test="${status.end gt 0}">
					<div class="highlightcolorBlack">
						<button type="submit" name="removeAnswer"
							value="${question.answers[status.index].id}" class="button">Remove
							answer</button>
					</div>
				</c:if>
				<form:errors path="answers[${status.index}].content" cssClass="errorMessage" />
			</c:forEach>
			<br />
		</c:if>
		<div class="highlightcolorBlack">
			<button type="submit" name="addAnswer" class="button">Add
				answer</button>
		</div>
	</fieldset>
	<div class="highlightcolorBlack">
		<button type="submit" name="save" class="button">Save</button>
		<button type="submit" name="cancel" class="button">Cancel</button>
	</div>
</form:form>