<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<jsp:include page="../fragments/header.jsp"/>
<c:url var="addUrl" value="/shops/add"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Shop list</h2>
    <table class="w3-table-all w3-hoverable w3-card-4">
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th></th>
        </tr>
        <c:forEach items="${shops}" var="shop">
            <c:url var="editUrl" value="/shops/edit/${shop.id}"/>
            <c:url var="deleteUrl" value="/shops/delete/${shop.id}"/>
            <tr>
                <td><c:out value="${shop.name}"/></td>
                <td><c:out value="${shop.address}"/></td>
                <td><c:out value="${shop.phone}"/></td>
                <td class="w3-right"><a href="${editUrl}"><i class="fa fa-edit"></i></a> <a href="${deleteUrl}"><i
                        class="fa fa-close"></i></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="${addUrl}">
        <p>
            <button class="w3-btn w3-gray2" a>Add a shop</button>
        </p>
    </a>
</div>
</body>
</html>