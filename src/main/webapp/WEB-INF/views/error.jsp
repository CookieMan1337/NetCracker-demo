<html>
<jsp:include page="fragments/header.jsp"/>
<body>
<div class="w3-center w3-container w3-content">
    <h1>Oops, we got an error.</h1>
    <h3><p>We suppose you opened a wrong link, or something crashed on server-side, we so sorry. </p></h3>
    <h3><p>Try to return to a main page and use graphical interface, it must works properly.</p></h3>

    <a href="${pageContext.request.contextPath}/">
        <p>
            <button class="w3-btn w3-green">Return</button>
        </p>
    </a>

</div>
</body>
</html>
