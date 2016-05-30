<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h2>
	<fmt:message key="page.addEditQuestion.header" />
</h2>
<c:url var="action" value="/questions" />
<form:form id="formQuestion" action="${action}" method="post"
	modelAttribute="question">
	<fieldset>
		<form:hidden path="id" />
		<form:label path="content">
			<fmt:message key="common.label.content" />
		</form:label>
		<form:input path="content" cssErrorClass="error" />
		<form:errors path="content" cssClass="errorMessage" />
		<br />
		<form:label path="category">
			<fmt:message key="page.addEditQuestion.label.category" />
		</form:label>
		<form:select path="category">
			<form:option value="-1">
				<fmt:message key="page.addEditQuestion.label.category.noCategory" />
			</form:option>
			<form:options items="${categories}" itemLabel="name" itemValue="id" />
		</form:select>
		<br />
		<form:label path="answers">
			<fmt:message key="page.addEditQuestion.label.answers" />
		</form:label>
		<form:errors path="answers" cssClass="errorMessage" />
		<br />
		<c:if test="${fn:length(question.answers) gt 0}">
			<c:forEach begin="0" end="${fn:length(question.answers)-1}"
				varStatus="status">
				<form:input path="answers[${status.index}].content"
					cssErrorClass="error" />
				<form:label path="answers[${status.index}].correct">
					<fmt:message key="page.addEditQuestion.label.answers.correct" />
				</form:label>
				<form:checkbox path="answers[${status.index}].correct" />
				<c:if test="${status.end gt 0}">
					<div class="highlightcolorBlack">
						<button type="submit" name="removeAnswer"
							value="${status.index}" class="button">
							<fmt:message key="page.addEditQuestion.action.removeAnswer" />
						</button>
					</div>
				</c:if>
				<form:errors path="answers[${status.index}].content"
					cssClass="errorMessage" />
			</c:forEach>
			<br />
		</c:if>
		<div class="highlightcolorBlack">
			<button type="submit" name="addAnswer" class="button">
				<fmt:message key="page.addEditQuestion.action.addAnswer" />
			</button>
		</div>
	</fieldset>
	<div class="highlightcolorBlack">
		<button type="submit" name="save" class="button">
			<fmt:message key="common.action.save" />
		</button>
		<button type="submit" name="cancel" class="button">
			<fmt:message key="common.action.cancel" />
		</button>
	</div>
</form:form>