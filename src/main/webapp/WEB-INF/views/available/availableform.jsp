<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<jsp:include page="../fragments/header.jsp"/>
<c:url var="saveUrl" value="/available/update"/>
<body>

<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Edit an available</h2>
    <form:form modelAttribute="availableAttribute" method="POST" action="${saveUrl}"
               cssClass="w3-panel w3-card-4 w3-white">
        <table class="w3-table">
            <tr>
                <td class="w3-padding-16"><form:label path="item_id">Item:</form:label></td>
                <td><form:select path="item_id" cssClass="w3-select" disabled="${edited}">
                    <c:forEach items="${itemlist}" var="item">
                        <%--
                        <c:choose>
                            <c:when test="${model.id eq itemAttribute.bodytype}">
                                <form:option value="${model.id}" selected="selected"><c:out value="${model.name}"/></form:option>
                            </c:when>
                            <c:otherwise>
                                <form:option value="${model.id}"><c:out value="${model.name}"/></form:option>
                            </c:otherwise>
                        </c:choose>
                        --%>
                        <form:option value="${item.id}"><c:out
                                value="${item.itemModel.name}  ${item.name}"/></form:option>
                    </c:forEach>


                </form:select>
                    <c:if test="${edited}"> <form:input path="item_id" type="hidden"/></c:if>
                </td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="storage_id">Storage:</form:label></td>
                <td>
                    <form:select path="storage_id" cssClass="w3-select" disabled="${edited}">
                        <c:forEach items="${storagelist}" var="storage">
                            <%--<c:choose>
                                <c:when test="${bodytype.id eq itemAttribute.bodytype}">
                                    <form:option value="${bodytype.id}" selected="true"><c:out value="${bodytype.name}"/></form:option>
                                </c:when>
                                <c:otherwise>
                                    <form:option value="${bodytype.id}"><c:out value="${bodytype.name}"/></form:option>
                                </c:otherwise>
                            </c:choose>
                            --%>
                            <form:option value="${storage.id}"><c:out value="${storage.name}"/></form:option>
                        </c:forEach>
                    </form:select>
                    <c:if test="${edited}"> <form:input path="storage_id" type="hidden"/></c:if>
                    <form:errors path="storage_id" cssClass="error"/>

                </td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="quantity">Quantity:</form:label></td>
                <td><form:input path="quantity" cssClass="w3-input" placeholder="Quantity" type="number"
                                min="1" max="10000000" required="required"/>
                    <form:errors path="quantity" cssClass="error"/></td>
            </tr>

            <tr>
                <td class="w3-padding-16"><form:label path="deliverytime">Delivery time:</form:label></td>
                <td><form:input path="deliverytime" cssClass="w3-input" placeholder="Delivery time" type="number"
                                min="0" max="10000" required="required"/>
                    <form:errors path="deliverytime" cssClass="error"/></td>
            </tr>
            <form:input path="action" type="hidden"/>
        </table>

        <p class="w3-center"><input type="submit" value="${edited ? "Update" : "Save"}" class="w3-btn w3-gray2"/></p>
    </form:form>
</div>
</body>
</html>