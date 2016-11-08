<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject" %>


<div class="page-header">
	<h3>${manufacturer.id} ${manufacturer.name}</h3>
	<div  class="btn-group">
		<button type="button" class="btn btn-success dropdown-toggle btn-sm" data-toggle="dropdown">
			Действие <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.SET_MANUFACTURER %>?id=${manufacturer.id}" >Редактировать</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.DEL_MANUFACTURER %>?id=${manufacturer.id}" >Удалить</a></li>
		</ul>
	</div>
</div>




<div class="panel panel-primary">
	<div class="panel-heading">
		<strong>Просмотреть производителя</strong>
	</div>
	<div class="panel-body">

	<div class="input-group input-group-mod">
		<span class="input-group-addon">ID</span> 
		<span><input type="text" class="form-control" value="${manufacturer.id}" readonly></span>
	</div>

	<div class="input-group input-group-mod">
		<span class="input-group-addon">Название</span> 
		<span><input type="text" class="form-control" value="${manufacturer.name}" readonly></span>
	</div>

	<div class="input-group input-group-mod">
		<span class="input-group-addon">Изображение</span> 
		<span class="form-control" ><img src="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.IMAGE_BOX_URL %>/${manufacturer.urlImage}"></span>
	</div>

	<div class="input-group input-group-mod">
		<span class="input-group-addon">Описание</span> 
		<span><textarea  readonly  class="form-control">${manufacturer.description}</textarea></span>
	</div>

	<div class="input-group input-group-mod">
		<span class="input-group-addon">Страна</span> 
		<span><input type="text" class="form-control" value="${manufacturer.country}" readonly></span>
	</div>

</div>
</div>