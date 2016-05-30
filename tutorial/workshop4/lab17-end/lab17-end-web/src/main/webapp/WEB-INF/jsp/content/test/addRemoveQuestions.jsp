<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><fmt:message key="page.addRemoveQuestions.header" /></h2>
<c:url var="action" value="/tests" />
<form:form id="formAddRemoveQuestions" action="${action}" method="post">
	<fieldset>
		<input type="hidden" name="testId" value="${test}" />
		<table>
			<tbody>
				<tr>
					<td>
						<label for="assignedQuestions"><fmt:message key="page.addRemoveQuestions.label.assignedQuestions" /></label>
						<br/>
						<select name="assignedQuestions" size="10" class="multiRowSelect">
							<c:forEach items="${assignedQuestions}" var="question">
								<option value="<c:out value="${question.id}"/>"><c:out value="${question.content}"/></option>
							</c:forEach>
						</select>
						<c:if test="${not empty removeQuestionRequired}">
							<br/>
							<span class="errorMessage"><fmt:message key="${removeQuestionRequired}" /></span>
						</c:if>
					</td>
					<td>
						<div class="highlightcolorBlack">
							<button type="submit" name="addQuestion" class="button"><fmt:message key="page.addRemoveQuestions.action.addQuestion" /></button>
						</div>
						<div class="highlightcolorBlack">
							<button type="submit" name="removeQuestion" class="button"><fmt:message key="page.addRemoveQuestions.action.removeQuestion" /></button>
						</div>
					</td>
					<td>
						<label for="nonAssignedQuestions"><fmt:message key="page.addRemoveQuestions.label.nonAssignedQuestions" /></label>
						<br/>
						<select name="nonAssignedQuestions" size="10" class="multiRowSelect">
							<c:forEach items="${nonAssignedQuestions}" var="question">
								<option value="<c:out value="${question.id}"/>"><c:out value="${question.content}"/></option>
							</c:forEach>
						</select>
						<c:if test="${not empty addQuestionRequired}">
							<br/>
							<span class="errorMessage"><fmt:message key="${addQuestionRequired}" /></span>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
	<div class="highlightcolorBlack">
		<button type="submit" name="cancel" class="button"><fmt:message key="page.addRemoveQuestions.action.back" /></button>
	</div>
</form:form>
