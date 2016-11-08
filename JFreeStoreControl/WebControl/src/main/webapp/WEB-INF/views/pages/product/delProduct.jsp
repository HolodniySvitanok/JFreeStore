<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>

<div class="panel panel-danger">
	<div class="panel-heading">
		<strong>Удалить товар</strong>
	</div>
	<div class="panel-body">
		<form class="navbar-form navbar-center" action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.ACTION_DEL_PRODUCT %>" method="get">
			<h3>${product.id} ${product.category.name} ${product.manufacturer.name} ${product.mainDescription}</h3>
			<input type="hidden" name="id" value="${product.id}">
			<input type="submit" value="Удалить"  class="btn btn-danger">
		</form>
	</div>
</div>

