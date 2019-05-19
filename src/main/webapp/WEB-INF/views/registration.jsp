<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<div class="w3-container w3-content" style="max-width: 700px;">

    <div class="w3-panel w3-card w3-white">
        <c:if test="${not empty param.login_error}">
    <span style="color: red; ">
      Your login attempt was not successful, try again.<br/><br/>
      Reason: Invalid username or password
    </span>
        </c:if>
        <%--        <form class="w3-container" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="w3-section">
                        <label><b>Username</b></label>
                        <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Username" name="username" required id="username">
                        <label><b>Password</b></label>
                        <input class="w3-input w3-border" type="password" placeholder="Enter Password" name="password" required id="password">
                        <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">Login</button>
                        <input class="w3-check w3-margin-top" type="checkbox" checked="checked"> Remember me
                        <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"
                               value="<c:out value="${_csrf.token}"/>"/>
                    </div>
                </form>--%>
        <form:form method="post" modelAttribute="user" action="/signin">
            <table class="w3-table">
                <form:input path="id" type="hidden"/>
                <tr>
                    <td class="w3-padding-16"><form:label path="login">Login:</form:label></td>
                    <td><form:input path="login" cssClass="w3-input" placeholder="Login" type="text"
                                    maxlength="20" required="required"/>
                        <form:errors path="login" cssClass="error"/></td>
                </tr>
                <tr>
                    <td class="w3-padding-16"><form:label path="password">Password:</form:label></td>
                    <td><form:input path="password" cssClass="w3-input" type="password" maxlength="20"
                                    required="required"/>
                        <form:errors path="password" cssClass="error"/></td>
                </tr>
                <tr>
                    <td class="w3-padding-16"><form:label path="firstname">First Name:</form:label></td>
                    <td><form:input path="firstname" cssClass="w3-input" placeholder="First Name" type="text"
                                    maxlength="20" required="required"/>
                        <form:errors path="firstname" cssClass="error"/></td>
                </tr>
                <tr>
                    <td class="w3-padding-16"><form:label path="lastname">Last Name:</form:label></td>
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
                    <%--<form:input path="profile" type="hidden"/>--%>

            </table>
            <p class="w3-center"><input type="submit" value="Register" class="w3-btn w3-green "/></p>
        </form:form>
    </div>
</div>
</body>
</html>