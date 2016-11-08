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
		<strong>Редактировать производителя</strong>
	</div>
	<div class="panel-body">
		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.SET_MANUFACTURER %>" enctype="multipart/form-data" method="POST">

			<div class="input-group input-group-mod">
				<span class="input-group-addon">ID</span> <span><input type="text" name="id" class="form-control" value="${manufacturer.id}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название</span> <span><input type="text" name="name" class="form-control" value="${manufacturer.name}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Страна</span> <span><input type="text" name="country" class="form-control" value="${manufacturer.country}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Описание</span> <span><textarea class="form-control" name="description">${manufacturer.description}</textarea></span>
			</div>


			<div class="input-group input-group-mod">
				<span class="input-group-addon">Изображение</span> 
				<span><input type="file" name="fileImage" class="form-control"></span>
			</div>

			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-success"></span>
			</div>

		</form>

	</div>
</div>