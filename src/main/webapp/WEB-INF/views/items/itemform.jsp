<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<jsp:include page="../fragments/header.jsp"/>
<c:url var="saveUrl" value="/items/update"/>
<body>
<div class="w3-container w3-content w3-center" style="max-width: 700px;">
    <h2>Edit item</h2>
    <form:form modelAttribute="itemAttribute" method="POST" action="${saveUrl}" cssClass="w3-panel w3-card-4 w3-white">
        <table class="w3-table">
            <form:input path="id" type="hidden"/>
            <tr>
                <td class="w3-padding-16"><form:label path="model">Model:</form:label></td>
                <td><form:select path="model" cssClass="w3-select">

                    <c:forEach items="${modellist}" var="model">
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
                        <form:option value="${model.id}"><c:out value="${model.name}"/></form:option>
                    </c:forEach>
                </form:select>
                </td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="name">Name:</form:label></td>
                <td><form:input path="name" cssClass="w3-input" placeholder="Name" type="text"
                                maxlength="50" required="required"/>
                    <form:errors path="name" cssClass="error"/></td>
            </tr>

            <tr>
                <td class="w3-padding-16"><form:label path="color">Color:</form:label></td>
                <td><form:input path="color" cssClass="w3-input" placeholder="Color" type="text"
                                maxlength="50" required="required"/>
                    <form:errors path="color" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="bodytype">Bodytype:</form:label></td>
                <td>
                    <form:select path="bodytype" cssClass="w3-select">
                        <c:forEach items="${bodylist}" var="bodytype">
                            <c:choose>
                                <c:when test="${bodytype.id eq itemAttribute.bodytype}">
                                    <form:option value="${bodytype.id}" selected="true"><c:out
                                            value="${bodytype.name}"/></form:option>
                                </c:when>
                                <c:otherwise>
                                    <form:option value="${bodytype.id}"><c:out value="${bodytype.name}"/></form:option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>

                </td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="price">Price:</form:label></td>
                <td><form:input path="price" cssClass="w3-input" placeholder="Price" type="number"
                                min="0" max="10000000" required="required"/>
                    <form:errors path="price" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="w3-padding-16"><form:label path="description">Description:</form:label></td>
                <td><form:textarea path="description" cssClass="w3-input" placeholder="Enter a desciption here"
                                   type="text"
                                   rows="4" maxlength="300" required="required" onkeyup="updateItemLettersCount(this)"
                                   id="itemTextArea"/>
                    <form:errors path="description" cssClass="error"/>
                    <label id="countLetters" class="w3-text-gray">Осталось
                        символов: ${300-itemAttribute.description.length()}</label>
                </td>
            </tr>
                <%-- <tr>
                     <c:forEach items="${shoplist}" var="shop">
                            <td>${shop.name}</td>
                     </c:forEach>
                 </tr>
                 --%>
        </table>
        <p class="w3-center"><input type="submit" value="${edited ? "Update" : "Save"}" class="w3-btn w3-gray2 "/></p>
    </form:form>
</div>
</body>
</html>