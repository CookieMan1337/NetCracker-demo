<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form class="w3-container" action="${pageContext.request.contextPath}/login" method="post">
            <div class="w3-section">
                <label><b>Username</b></label>
                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Username" name="login"
                       required id="username">
                <label><b>Password</b></label>
                <input class="w3-input w3-border" type="password" placeholder="Enter Password" name="password" required
                       id="password">
                <input class="w3-check w3-margin-top" type="checkbox" checked="checked" name="remember-me"> Remember me
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="w3-center">
                    <button class="w3-button w3-green w3-padding" type="submit">Login</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>