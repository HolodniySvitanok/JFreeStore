<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject" %>

<div class="panel panel-danger">
	<div class="panel-heading">
		<strong>Удалить категорию</strong>
	</div>
	<div class="panel-body">
		<form class="navbar-form navbar-center" action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.ACTION_DEL_CATEGORY %>" method="get">
			<h3>${category.id} ${category.name}</h3>
			<input type="hidden" name="id" value="${category.id}">
			<input type="submit" value="Удалить"  class="btn btn-danger">
		</form>
	</div>
</div>

