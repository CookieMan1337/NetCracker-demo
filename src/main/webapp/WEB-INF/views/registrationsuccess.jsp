<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Confirmation Page</title>
</head>
<jsp:include page="fragments/header.jsp"/>
<body>
<div class="w3-container w3-content" style="max-width: 700px;">

    <div class="w3-panel w3-pale-green w3-round w3-card-4">
        <h3>Success!</h3>
        <p>${success}</p>
        <p>Now you can <a href="${pageContext.request.contextPath}/login">log in</a> now.</p>
    </div>
</div>
</body>

</html>