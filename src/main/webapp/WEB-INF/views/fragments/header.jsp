<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Lora&subset=cyrillic,cyrillic-ext,latin-ext" rel="stylesheet">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/masking-input.css"/>--%>
    <script type="text/javascript" async="" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
    <%--<script src="${pageContext.request.contextPath}/resources/js/masking-input.js" data-autoinit="true"></script>`--%>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Autoshop</title>
</head>

<style>
    body, h1, h2, h3, h4, h5 {
        font-family: 'Lora', serif;
    }
</style>

<body class="w3-body">
<div class="w3-bar w3-card w3-header">
    <a href="${pageContext.request.contextPath}/buy/" class="w3-bar-item w3-button">Buy items</a>
    <a href="${pageContext.request.contextPath}/stores" class="w3-bar-item w3-button">Out stores</a>
    <a href="${pageContext.request.contextPath}/information" class="w3-bar-item w3-button">Information about us</a>
    <sec:authorize access="isAnonymous()">
        <a href="${pageContext.request.contextPath}/login" class="w3-bar-item w3-button w3-right">Log in</a>
        <a href="${pageContext.request.contextPath}/signin" class="w3-bar-item w3-button w3-right">Sign in</a>
    </sec:authorize>
    <%--100% must work for all users--%>
    <sec:authorize access="isAuthenticated()">
        <div class="w3-right">
            <div class="w3-dropdown-hover">
                <button class="w3-button">Hello, ${loggedinuser}</button>
                <div class="w3-dropdown-content w3-bar-block w3-card-2">
                    <a href="${pageContext.request.contextPath}/profile/" class="w3-bar-item w3-button">Profile</a>
                    <a href="${pageContext.request.contextPath}/profile/orders" class="w3-bar-item w3-button">Orders</a>
                    <a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button">Log out</a>
                </div>
            </div>
            <sec:authorize access="hasAnyRole('MODER', 'ADMIN')">
                <div class="w3-dropdown-hover">
                    <button class="w3-button">Admin</button>
                    <div class="w3-dropdown-content w3-bar-block w3-card-2 w3-header">
                        <a href="${pageContext.request.contextPath}/items/" class="w3-bar-item w3-button">Items</a>
                        <a href="${pageContext.request.contextPath}/itemmodels/" class="w3-bar-item w3-button">Item
                            models</a>
                        <a href="${pageContext.request.contextPath}/bodytypes/" class="w3-bar-item w3-button">Item
                            bodytypes</a>
                        <a href="${pageContext.request.contextPath}/shops/" class="w3-bar-item w3-button">Shops</a>
                        <a href="${pageContext.request.contextPath}/storages/"
                           class="w3-bar-item w3-button">Storages</a>
                        <a href="${pageContext.request.contextPath}/available/"
                           class="w3-bar-item w3-button">Availables</a>
                        <sec:authorize access="hasRole('ADMIN')">
                            <a href="${pageContext.request.contextPath}/users/" class="w3-bar-item w3-button">Users</a>
                        </sec:authorize>
                    </div>
                </div>
            </sec:authorize>
            <a href="${pageContext.request.contextPath}/cart/" class="w3-button w3-bar-item"><i
                    class="fa fa-shopping-cart"></i> Your cart</a>
        </div>
    </sec:authorize>
</div>
<%-- --%>


</body>


