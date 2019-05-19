<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/w3.css">
<html>

<jsp:include page="../fragments/header.jsp"/>
<c:url var="addUrl" value="/items/add"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Item list</h2>
    <table class="w3-table-all w3-hoverable w3-card-4">
        <tr>
            <th>Name</th>
            <th>Model</th>
            <th>Color</th>
            <th>Bodytype</th>
            <th>Price</th>
            <th></th>
        </tr>
        <c:forEach items="${items}" var="item">
            <c:url var="editUrl" value="/items/edit/${item.id}"/>
            <c:url var="deleteUrl" value="/items/delete/${item.id}"/>
            <tr>
                <td><c:out value="${item.itemModel.name}"/></td>
                <td><c:out value="${item.name}"/></td>
                <td><c:out value="${item.color}"/></td>
                <td><c:out value="${item.itembodytype.name}"/></td>
                <td><c:out value="${item.price}"/></td>
                <td class="w3-right"><a href="${editUrl}"><i class="fa fa-edit"></i></a> <a href="${deleteUrl}"><i
                        class="fa fa-close"></i></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="${addUrl}">
        <p>
            <button class="w3-btn w3-gray2" a>Add an item</button>
        </p>
    </a>

</div>

</body>
</html>