<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<jsp:include page="../fragments/header.jsp"/>
<c:url var="saveUrl" value="/profile/updateinfo"/>
<c:url var="savePassUrl" value="/profile/updatepass"/>
<body>
<div class="w3-container w3-content" style="max-width: 700px;">
    <h2 class="w3-center">Your orders</h2>
    <!-- Sidebar -->
    <div class="w3-border w3-card w3-white">
        <div class="w3-sidebar-inside w3-bar-block w3-center " style="width:150px">
            <h3></h3>
            <a href="${pageContext.request.contextPath}/profile/"
               class="w3-bar-item w3-hover-text-indigo no-decor"><span>Profile</span></a>
            <a href="${pageContext.request.contextPath}/profile/orders"
               class="w3-bar-item w3-hover-text-indigo no-decor"><span>Orders</span></a>
            <a href="#" class="w3-bar-item w3-hover-text-indigo no-decor"><span>Feedback</span></a>
        </div>

        <!-- Page Content -->
        <div style="margin-left:150px">
            <%--<c:if test="${not empty msg}">
                <div class="w3-panel w3-pale-green w3-round-large">
                    <p>${msg}</p>
                </div>
            </c:if>--%>
            <h3 class="w3-center">Order history</h3>
            <c:choose>
                <c:when test="${empty purchaseList}">
                    <p>
                        Oops, you haven't buy anything in our shop.<br/><br/>
                        <a href="/buy/">Check our items!</a>
                    </p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${purchaseList}" var="purchase">
                        <div class="w3-panel">
                            <span><b>Order #: ${purchase.id}</b></span>
                            <table class="w3-table">
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                                <c:set var="totalPrice" value="${0}"/>
                                <c:forEach items="${purchase.purchaseOrders}" var="order">
                                    <tr>
                                        <td>${order.item.itemModel.name} ${order.item.name}</td>
                                        <td>${order.quantity}</td>
                                        <td>${order.item.price*order.quantity}</td>
                                    </tr>
                                    <c:set var="totalPrice" value="${totalPrice+order.quantity * order.item.price}"/>
                                </c:forEach>
                                <tr>
                                    <td></td>
                                    <td><b>Total price:</b></td>
                                    <td>${totalPrice}</td>
                                </tr>
                            </table>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>


    </div>
</div>
</body>
</html>
