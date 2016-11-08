<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>



<div class="alert alert-info">
	<strong>${reviewUser.id} ${reviewUser.name}</strong>
</div>
<p>
	<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.SET_USER %>?id=${reviewUser.id}" class="btn btn-primary btn-sm active" role="button">Редактировать</a> 
	<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.DEL_USER %>?id=${reviewUser.id}" class="btn btn-default btn-sm active" role="button">Удалить</a>
</p>



<div class="panel panel-primary">
	<div class="panel-heading">
		<strong>Просмотреть пользователя</strong>
	</div>
	<div class="panel-body">


			<div class="input-group input-group-mod">
				<span class="input-group-addon">ID</span> 
				<span><input type="number" class="form-control" value="${reviewUser.id}" readonly></span>
			</div>
			
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Имя</span> 
				<span><input type="text" class="form-control" value="${reviewUser.name}"  readonly></span>
			</div>
			
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Логин</span> 
				<span><input type="text" class="form-control" value="${reviewUser.login}"  readonly></span>
			</div>
			
			<div class="input-group input-group-mod">
				<span class="input-group-addon">E-mail</span> 
				<span><input type="text" class="form-control" value="${reviewUser.email}"  readonly></span>
			</div>
			
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Телефон</span> 
				<span><input type="text" class="form-control" value="${reviewUser.mobilePhone}"  readonly></span>
			</div>
			
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Урв. доступа</span> 
				<span><input type="text" class="form-control" value="${reviewUser.accessLevel}" readonly></span>
			</div>


</div>
</div>