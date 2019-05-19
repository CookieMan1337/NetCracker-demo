<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="../fragments/header.jsp"/>
<c:url var="saveUrl" value="/shops/update"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Edit shop</h2>
    <form:form modelAttribute="shopAttribute" method="POST" action="${saveUrl}" cssClass="w3-panel w3-card-4 w3-white">
        <table class="w3-table">
            <form:input path="id" type="hidden"/>
            <tr>
                <td class="w3-padding-16"><form:label path="name">Name:</form:label></td>
                <td><form:input path="name" cssClass="w3-input" placeholder="Name" type="text"
                                maxlength="50" required="required"/>
                    <form:errors path="name" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="address">Address:</form:label></td>
                <td><form:input path="address" cssClass="w3-input" placeholder="Address" type="text"
                                maxlength="50" required="required"/>
                    <form:errors path="address" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="phone">Phone:</form:label></td>
                <td><form:input path="phone" cssClass="w3-input" placeholder="+7(920)250-11-11" type="tel"
                                pattern="\+7\([0-9]{3}\)[0-9]{3}\-[0-9]{2}\-[0-9]{2}"
                                maxlength="16" required="required" title="Please enter valid phone number"/>
                    <form:errors path="phone" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="storages">Storages:</form:label></td>
                <td><form:select path="storages" cssClass="w3-selectmultiple" multiple="true" size="5"
                                 required="required">
                    <form:option value="-1" disabled="true">---Choose one or many options---</form:option>
                    <c:forEach items="${storageList}" var="storage">
                        <c:set var="flag" value="false" scope="page"/>
                        <c:forEach items="${shopAttribute.storages}" var="shopStorage">
                            <c:choose>
                                <c:when test="${storage.id eq shopStorage}">
                                    <c:set var="flag" value="true"/>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${flag eq true}">
                                <form:option value="${storage.id}" selected="true"><c:out
                                        value="${storage.name}"/></form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value="${storage.id}"><c:out value="${storage.name}"/></form:option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
                    <form:errors path="storages" cssClass="error"/>
                </td>
            </tr>
        </table>

        <p class="w3-center"><input type="submit" value="${edited ? "Update" : "Save"}" class="w3-btn w3-gray2 "/></p>
    </form:form>
</div>
</body>
</html>