<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/w3.css">
<html>

<jsp:include page="../fragments/header.jsp"/>
<c:url var="addUrl" value="/available/add"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Availables list</h2>
    <table class="w3-table-all w3-hoverable w3-card-4">
        <tr>
            <th>Item</th>
            <th>Storage</th>
            <th>Quantity</th>
            <th>Delivery time</th>
            <th></th>
        </tr>
        <c:forEach items="${availables}" var="available">
            <c:url var="editUrl" value="/available/edit/${available.item.id}/${available.storage.id}"/>
            <c:url var="deleteUrl" value="/available/delete/${available.item.id}/${available.storage.id}"/>
            <tr>
                <td><c:out value="${available.item.itemModel.name} ${available.item.name}"/></td>
                <td><c:out value="${available.storage.name}"/></td>
                <td><c:out value="${available.quantity}"/></td>
                <td><c:out value="${available.deliverytime}"/></td>
                <td class="w3-right"><a href="${editUrl}"><i class="fa fa-edit"></i></a> <a href="${deleteUrl}"><i
                        class="fa fa-close"></i></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="${addUrl}">
        <p>
            <button class="w3-btn w3-gray2" a>Add an item to storages</button>
        </p>
    </a>

</div>

</body>
</html>