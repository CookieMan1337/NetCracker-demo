<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<jsp:include page="../fragments/header.jsp"/>
<body>

<div class="w3-container w3-content" style="max-width: 900px;">

    <h2 class="w3-center">All items</h2>
    <div class="w3-bar w3-center">
        <a href="#" class="w3-button">&laquo;</a>
        <a href="#" class="w3-button">1</a>
        <a href="#" class="w3-button">2</a>
        <a href="#" class="w3-button">3</a>
        <a href="#" class="w3-button">4</a>
        <a href="#" class="w3-button">5</a>
        <a href="#" class="w3-button">&raquo;</a>
    </div>
    <c:forEach items="${items}" var="item">
        <%--    <c:url var="editUrl" value="/itemmodels/edit/${itemmodel.id}" />
            <c:url var="deleteUrl" value="/itemmodels/delete/${itemmodel.id}" />--%>
        <div class="w3-panel w3-round-large w3-border w3-card w3-border-gray w3-padding-16 w3-white">
            <div class="w3-left w3-twothird">
                <b> ${item.itembodytype.name} ${item.itemModel.name} ${item.name}, ${item.color}</b>
                <p class="w3-description">${item.description}</p>
            </div>
            <div class="w3-right w3-section w3-center " style="width: 120px">
                <button class="w3-button w3-round w3-orange w3-text-white w3-text-shadow"
                        onclick="addOrderToCart(${item.id})"><i class="fa fa-shopping-cart w3-medium"
                                                                aria-hidden="true"></i> Buy
                </button>
                <br/>
            </div>
            <div class="w3-rest w3-right w3-margin-right w3-rest">
                <p class="w3-large">${item.price} <i class="fa fa-rub w3-medium w3-text-gray" aria-hidden="true"></i>
                </p>
            </div>

        </div>

    </c:forEach>
</div>
</body>
</html>