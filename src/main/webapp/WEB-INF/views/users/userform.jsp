<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="../fragments/header.jsp"/>

<c:url var="saveUrl" value="/users/update"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>User shop</h2>
    <form:form modelAttribute="userAttribute" method="POST" action="${saveUrl}" cssClass="w3-panel w3-card-4 w3-white">
        <table class="w3-table">
            <form:input path="id" type="hidden"/>
            <form:input path="password" type="hidden" value="00000000"/>
            <tr>
                <td class="w3-padding-16"><form:label path="login">Login:</form:label></td>
                <td><form:input path="login" cssClass="w3-input" placeholder="Login" type="text"
                                maxlength="20" required="required"/>
                    <form:errors path="login" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="firstname">First name:</form:label></td>
                <td><form:input path="firstname" cssClass="w3-input" placeholder="First Name" type="text"
                                maxlength="20" required="required"/>
                    <form:errors path="firstname" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="lastname">Last name:</form:label></td>
                <td><form:input path="lastname" cssClass="w3-input" placeholder="Last Name" type="text"
                                maxlength="20" required="required"/>
                    <form:errors path="lastname" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="email">Email:</form:label></td>
                <td><form:input path="email" cssClass="w3-input" placeholder="Email" type="text"
                                maxlength="50" required="required"/>
                    <form:errors path="email" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="userProfiles">Roles:</form:label></td>
                <td><form:select path="userProfiles" cssClass="w3-selectmultiple" multiple="true" size="4"
                                 required="required">
                    <form:option value="-1" disabled="true">---Choose one or many options---</form:option>
                    <c:forEach items="${userProfileList}" var="userProfile">
                        <c:set var="flag" value="false" scope="page"/>
                        <c:forEach items="${userAttribute.userProfiles}" var="profile">
                            <c:choose>
                                <c:when test="${userProfile.id eq profile.id}">
                                    <c:set var="flag" value="true"/>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${flag eq true}">
                                <form:option value="${userProfile.id}" selected="true"><c:out
                                        value="${userProfile.type}"/></form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value="${userProfile.id}"><c:out
                                        value="${userProfile.type}"/></form:option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
                    <form:errors path="userProfiles" cssClass="error"/>
                </td>
            </tr>
        </table>

        <p class="w3-center"><input type="submit" value="Save" class="w3-btn w3-gray2 "/></p>
    </form:form>
</div>
</body>
</html>
