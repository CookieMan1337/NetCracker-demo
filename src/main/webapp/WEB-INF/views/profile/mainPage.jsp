<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<jsp:include page="../fragments/header.jsp"/>
<c:url var="saveUrl" value="/profile/updateinfo"/>
<c:url var="savePassUrl" value="/profile/updatepass"/>
<body>
<div class="w3-container w3-content" style="max-width: 700px;">
    <h2 class="w3-center">Your account</h2>
    <!-- Sidebar -->
    <div class="w3-border w3-card w3-white">
        <div class="w3-sidebar-inside w3-bar-block w3-center " style="width:150px">
            <h3></h3>
            <a href="${pageContext.request.contextPath}/profile/"
               class="w3-bar-item w3-hover-text-indigo no-decor"><span>Profile</span></a>
            <a href="${pageContext.request.contextPath}/profile/orders"
               class="w3-bar-item w3-hover-text-indigo no-decor"><span>Orders</span></a>
            <a href="#" class="w3-bar-item w3-hover-text-indigo no-decor"><span>Feedback</span></a>
        </div>

        <!-- Page Content -->
        <div style="margin-left:150px">
            <c:if test="${not empty msg}">
                <div class="w3-panel w3-pale-green w3-round-large">
                    <p>${msg}</p>
                </div>
            </c:if>
            <h3>Edit info</h3>
            <form:form modelAttribute="profileAttribute" method="POST" action="${saveUrl}" cssClass="w3-margin ">
                <form:input path="id" type="hidden"/>
                <%--<form:input path="login" type="hidden"/>--%>
                <form:errors path="id" cssClass="error"/>
                <form:input path="action" type="hidden" value="info"/>
                <div class="w3-margin-bottom">
                    <form:label path="firstname"><b>First name</b></form:label>
                    <form:input path="firstname" cssClass="w3-input" placeholder="Enter a first name" type="text"
                                maxlength="20" required="required"/>
                    <form:errors path="firstname" cssClass="error"/>
                </div>
                <div class="w3-margin-bottom">
                    <form:label path="lastname"><b>Last name</b></form:label>
                    <form:input path="lastname" cssClass="w3-input" placeholder="Enter a last name" type="text"
                                maxlength="20" required="required"/>
                    <form:errors path="lastname" cssClass="error"/>
                </div>
                <div class="w3-margin-bottom">
                    <form:label path="email"><b>Email</b></form:label>
                    <form:input path="email" cssClass="w3-input" placeholder="Enter a email" type="text"
                                required="required"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
                <p class="w3-center"><input type="submit" value="Update info" class="w3-btn w3-green "/></p>
            </form:form>

        </div>

        <div style="margin-left: 150px;">
            <h3>Edit password</h3>
            <form:form modelAttribute="profileAttribute" method="POST" action="${savePassUrl}" cssClass="w3-margin">
                <form:input path="id" type="hidden"/>
                <%-- <form:input path="login" type="hidden"/>--%>
                <form:errors path="id" cssClass="error"/>
                <form:input path="action" type="hidden" value="pass"/>
                <div class="w3-margin-bottom">
                    <form:label path="oldPassword"><b>Old password</b></form:label>
                    <form:input path="oldPassword" cssClass="w3-input w3-" type="password"
                                maxlength="20" required="required" value=""/>
                    <form:errors path="oldPassword" cssClass="error"/>
                </div>
                <div class="w3-margin-bottom">
                    <form:label path="password"><b>Password</b></form:label>
                    <form:input path="password" cssClass="w3-input w3-" type="password"
                                maxlength="20" required="required" value=""/>
                    <form:errors path="password" cssClass="error"/>
                </div>
                <div class="w3-margin-bottom">
                    <form:label path="passwordConfirm"><b>Confirm password</b></form:label>
                    <form:input path="passwordConfirm" cssClass="w3-input" type="password"
                                maxlength="20" required="required" value=""/>
                    <form:errors path="passwordConfirm" cssClass="error"/>
                </div>
                <p class="w3-center"><input type="submit" value="Save password" class="w3-btn w3-green "/></p>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
