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
		<h3 class="panel-title">Добавить производителя</h3>
	</div>
	<div class="panel-body">



		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.ADD_MANUFACTURER %>" enctype="multipart/form-data" method="POST">

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название производителя</span> <span><input type="text" class="form-control" name="name" placeholder="введите строку" required></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Страна</span> <span><input type="text" class="form-control" name="country" placeholder="введите строку" required></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Описание</span> <span><textarea class="form-control" name="description" placeholder="введите строку"></textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Изображение	</span>
				<span><input type="file" class="form-control" name="fileImage"></span>
			</div>


			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-default"></span>
			</div>

		</form>
	</div>
</div>