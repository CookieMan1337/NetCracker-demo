<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:url var="saveUrl" value="/itemmodels/update"/>
<jsp:include page="../fragments/header.jsp"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Edit item model</h2>
    <form:form modelAttribute="itemmodelAttribute" method="POST" action="${saveUrl}"
               cssClass="w3-panel w3-card-4 w3-white">
        <table class="w3-table">
            <form:input path="id" type="hidden"/>
            <tr>
                <td class="w3-padding-16"><form:label path="name">Name:</form:label></td>
                <td><form:input path="name" cssClass="w3-input" placeholder="Name" type="text"
                                maxlength="50" required="required"/>
                    <form:errors path="name" cssClass="error"/></td>
            </tr>
        </table>

        <p class="w3-center"><input type="submit" value="${edited ? "Update" : "Save"}" class="w3-btn w3-gray2"/></p>
    </form:form>
</div>
</body>
</html>