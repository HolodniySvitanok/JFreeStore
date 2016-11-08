<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>



<div class="panel panel-danger">
	<div class="panel-heading">
		<strong>Удалить пользователя</strong>
	</div>
	<div class="panel-body">
		<form class="navbar-form navbar-center" action="${pageContext.servletContext.contextPath}<%= GlobalConfigProject.USER %><%= GlobalConfigProject.DEL_USER %>" method="POST">
			<h3>${user.id}${user.name}</h3>
			<input type="hidden" name="id" value="${user.id}"> <input type="submit" value="Удалить" class="btn btn-danger">
		</form>
	</div>
</div>