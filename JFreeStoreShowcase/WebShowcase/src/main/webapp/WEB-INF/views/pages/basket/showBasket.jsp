<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.holodniysvitanok.conf.WebModuleConfig" %>

<div class="container container-mod">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <form method="POST" action="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.UPDATE_BASKET %>">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Продукт</th>
                        <th>Количество</th>
                        <th>Цена</th>
                        <th>Удалить</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="purchase" items="${purchasedList}">
                        <input type="hidden" value="${purchase.product.id}" name="id">
                        <tr>
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCT %>?id=${purchase.product.id}">${purchase.product.id} ${purchase.product.category.name} ${purchase.product.manufacturer.name} ${purchase.product.supportField1} ${purchase.product.supportField2}</a>
                            </td>
                            <td><input type="number" min="1" value="${purchase.count}" name="count"></td>
                            <td>${purchase.totalPrice}</td>
                            <td><input type="checkbox" name="delete" value="${purchase.product.id}"></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <input type="submit" class="btn btn-success" value="Обновить">
            </form>
            <hr>
            <div class="row">
                <div class="col-md-9"></div>
                <div class="col-md-3">Сумма: ${basketStatus.totalPrice} <%= WebModuleConfig.CURRENCY %>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>