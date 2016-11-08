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
		<h3 class="panel-title">Добавить заказ</h3>
	</div>
	<div class="panel-body">

		<form action="" method="POST">

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название заказа</span> <span><input type="text" class="form-control" name="name" placeholder="введите строку" required value="${nameOrder}"></span>
			</div>
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Описание заказа</span> <span><textarea name="description" placeholder="введите строку" class="form-control"></textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-sm btn-primary"></span>
			</div>

		</form>

	</div>
</div>