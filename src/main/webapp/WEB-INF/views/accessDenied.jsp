<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<div class="w3-container w3-content" style="max-width: 700px;">

    <div class="w3-panel w3-card w3-white w3-center">
        <p>Oops, friend, something goes wrong. Please <a href="${pageContext.request.contextPath}/">return</a> to main
            page and do not try to be an admin.
        </p>
    </div>
</div>
</body>
</html>
