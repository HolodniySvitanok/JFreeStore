<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>

<div class="panel panel-danger">
	<div class="panel-heading">
		<strong>Удалить заказ</strong>
	</div>
	<div class="panel-body">
 		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ACTION_DEL_ORDER %>" method="get">
			<h3>${order.name} ${order.description}</h3>
			<input type="hidden" name="id" value="${order.id}">
			<input type="submit" class="btn btn-danger" value="Удалить">
		</form>
	</div>
</div>

