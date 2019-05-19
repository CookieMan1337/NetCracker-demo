<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<jsp:include page="../fragments/header.jsp"/>
<body>

<div class="w3-container w3-content" style="max-width: 1000px;">

    <h2 class="w3-center">Your items in cart</h2>

    <%--    <c:url var="editUrl" value="/itemmodels/edit/${itemmodel.id}" />
        <c:url var="deleteUrl" value="/itemmodels/delete/${itemmodel.id}" />--%>
    <c:set var="totalPrice" scope="page" value="0"/>
    <div class="w3-panel w3-round-large w3-border w3-card w3-border-gray w3-padding-16 w3-white">
        <div class="w3-col w3-margin-top w3-margin-bottom">
            <div class="w3-left">
                <b class="w3-large"> Название </b>
            </div>
            <div class="w3-margin-left w3-right w3-margin-right" style="width:50px;margin-right: 40px!important;">
                <span class="w3-large w3-margin-right"><b>Price</b></span>
            </div>
            <div class="w3-right" style="width: 50px; margin-right: 25px;" ;>
                <span class="w3-large w3-margin-right"><b>Quantity</b></span>
            </div>
        </div>
        <c:forEach items="${orderList}" var="item">
            <div class="w3-col w3-margin-top w3-margin-bottom">
                <div class="w3-left">
                    <b> ${item.item.itembodytype.name} ${item.item.itemModel.name} ${item.item.name}, ${item.item.color}</b>
                    <br>
                    <br>
                    <span class="w3-description">Код товара: ${item.item.id}</span>
                </div>
                <div class="w3-margin-left w3-right w3-cell w3-padding-16">
                    <a href="/cart/delete/${item.item.id}" class="w3-cell-middle"><i class="fa fa-close"></i></a>
                </div>
                <div class="w3-margin-right w3-right">
                    <p class="w3-large">${item.quantity * item.item.price} <i class="fa fa-rub w3-medium w3-text-gray"
                                                                              aria-hidden="true"></i></p>
                    <c:set var="totalPrice" value="${totalPrice+item.quantity * item.item.price}"/>
                </div>
                <div class="w3-right w3-border-thin w3-round-large w3-cell w3-margin" style="width: 50px" ;>
                        <%--<button class="fa fa-minus w3-third w3-white w3-border-0 w3-cart-button"></button>--%>
                    <input type="number" min="1" max="10" id="${item.item.id}" style="width: inherit;"
                           class="w3-cell-middle" value="${item.quantity}"/>
                        <%--<button class="fa fa-plus w3-third w3-white w3-border-0 w3-cart-button" ></button>--%>
                </div>
            </div>

        </c:forEach>

        <div class="w3-container w3-cell w3-cell-middle w3-padding-16 w3-right w3-border-top">
            <div class=" w3-right">
                <span class="w3-description"> Итого:</span><br>
                <span class=" w3-text-black w3-large">${totalPrice} рублей</span>
            </div>
        </div>
        <div class="w3-container w3-cell w3-cell-middle w3-padding-16 w3-right w3-border-top">
            <div class=" w3-right">
                <a href="/cart/checkout" class="w3-button w3-round w3-orange w3-text-white w3-text-shadow">Proceed to
                    checkout</a>
            </div>
        </div>
    </div>


</div>
</body>
</html>