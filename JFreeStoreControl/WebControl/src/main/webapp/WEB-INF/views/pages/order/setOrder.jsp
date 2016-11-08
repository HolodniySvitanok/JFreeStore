<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>



<c:if test="${not empty message}">
	<div class="alert alert-danger">
		<strong>${message}</strong>
	</div>
</c:if>



<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Редактировать заказ</h3>
	</div>
	<div class="panel-body">


		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.SET_ORDER %>" method="POST">

			<div class="input-group input-group-mod">
				<span class="input-group-addon">ID</span> <span><input type="text" class="form-control" value="${order.id}" name="id" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название заказа</span> <span><input type="text" class="form-control" value="${order.name}" name="name"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Описание</span> <span><textarea name="description" class="form-control" placeholder="введите строку">${order.description}</textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Активный</span> <span><input type="text" class="form-control" value="${order.active}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Новый</span> <span  class="input-group-addon"> <input type="checkbox" name="newOrder" class="form-control" <c:if test="${order.newOrder}">checked</c:if>>
				</span>
			</div>

			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-sm btn-primary"></span>
			</div>



		</form>
	</div>
</div>