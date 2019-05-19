<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<jsp:include page="../fragments/header.jsp"/>
<c:url var="addUrl" value="/itemmodels/add"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Item models list</h2>
    <table class="w3-table-all w3-hoverable  w3-card-4">
        <tr>
            <th class="w3-center">Name</th>
            <th></th>
        </tr>
        <c:forEach items="${itemmodels}" var="itemmodel">
        <c:url var="editUrl" value="/itemmodels/edit/${itemmodel.id}"/>
        <c:url var="deleteUrl" value="/itemmodels/delete/${itemmodel.id}"/>
        <tr>
            <td class="w3-center"><c:out value="${itemmodel.name}"/></td>
            <td class="w3-right"><a href="${editUrl}"><i class="fa fa-edit"></i></a>
                <a href="${deleteUrl}"><i class="fa fa-close"></i></a></td>
            </c:forEach>

    </table>
    <a href="${addUrl}">
        <p>
            <button class="w3-btn w3-gray2" a>Add an item model</button>
        </p>
    </a>
</div>
</body>
</html>