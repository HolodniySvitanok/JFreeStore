<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>



<div class="page-header">
	<h3>${category.id} ${category.name}</h3>
	<div  class="btn-group">
		<button type="button" class="btn btn-success dropdown-toggle btn-sm" data-toggle="dropdown">
			Действие <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.SET_CATEGORY %>?id=${category.id}" >Редактировать</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.DEL_CATEGORY %>?id=${category.id}" >Удалить</a></li>
		</ul>
	</div>
</div>






<div class="panel panel-primary">
	<div class="panel-heading">
		<strong>Просмотреть производителя</strong>
	</div>
	<div class="panel-body">

		<div class="input-group input-group-mod">
			<span class="input-group-addon">ID</span> <span><input type="text" class="form-control" value="${category.id}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название</span> <span><input type="text" class="form-control" value="${category.name}" readonly></span>
		</div>
		
		<div class="input-group input-group-mod">
			<span class="input-group-addon">URL категории</span> <span><input type="text" class="form-control" value="${category.url}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Изображение</span> <span class="form-control"><img src="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.IMAGE_BOX_URL %>/${category.urlImage}"></span>
		</div>


		<div class="input-group input-group-mod">
			<span class="input-group-addon">Группа</span><span><input type="text" class="form-control" value="${category.group.name}" readonly></span>
		</div>
		<div class="input-group input-group-mod">
			<span class="input-group-addon">Описание</span> <span><textarea readonly class="form-control">${category.description}</textarea></span>
		</div>



		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #1</span> <span><input type="text" class="form-control" value="${category.supportFieldName1}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #2</span> <span><input type="text" class="form-control" value="${category.supportFieldName2}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #3</span> <span><input type="text" class="form-control" value="${category.supportFieldName3}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #4</span> <span><input type="text" class="form-control" value="${category.supportFieldName4}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #5</span> <span><input type="text" class="form-control" value="${category.supportFieldName5}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #6</span> <span><input type="text" class="form-control" value="${category.supportFieldName6}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #7</span> <span><input type="text" class="form-control" value="${category.supportFieldName7}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #8</span> <span><input type="text" class="form-control" value="${category.supportFieldName8}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #9</span> <span><input type="text" class="form-control" value="${category.supportFieldName9}" readonly></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">Название поле #10</span> <span><input type="text" class="form-control" value="${category.supportFieldName10}" readonly></span>
		</div>



	</div>
</div>