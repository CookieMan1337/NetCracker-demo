<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<div class="w3-container w3-content" style="max-width: 700px;">
    <div class="w3-panel w3-card w3-white " style="width: 700px">
        <h3 class="w3-center">Адреса наших магазинов</h3>
        <table class="w3-table w3-margin-bottom">
            <tr class="w3-text-gray">
                <td>Название</td>
                <td>Адрес</td>
                <td>Телефон</td>
            </tr>
            <c:forEach items="${shopList}" var="shop">
                <tr>
                    <td><c:out value="${shop.name}"/></td>
                    <td><c:out value="${shop.address}"/></td>
                    <td><c:out value="${shop.phone}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
