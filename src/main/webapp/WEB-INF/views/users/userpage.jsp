<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="../fragments/header.jsp"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Users list</h2>
    <table class="w3-table-all w3-hoverable w3-card-4">
        <tr>
            <th>Login</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th></th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <c:url var="editUrl" value="/users/edit/${user.id}"/>
            <c:url var="deleteUrl" value="/users/delete/${user.id}"/>
            <tr>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.firstname}"/></td>
                <td><c:out value="${user.lastname}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td class="w3-right"><a href="${editUrl}"><i class="fa fa-edit"></i></a> <a href="${deleteUrl}"><i
                        class="fa fa-close"></i></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
