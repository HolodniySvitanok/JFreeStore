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
		<strong>Редактировать категорию</strong>
	</div>
	<div class="panel-body">

		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.SET_CATEGORY %>" method="POST" enctype="multipart/form-data">

			<div class="input-group input-group-mod">
				<span class="input-group-addon">ID</span> <span><input type="text" name="id" class="form-control" value="${category.id}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название</span> <span><input type="text" name="name" class="form-control" value="${category.name}"></span>
			</div>
			<div class="input-group input-group-mod">
				<span class="input-group-addon">URL категории</span> <span><input type="text" required name="url" class="form-control" value="${category.url}"></span>
			</div>


			<div class="input-group input-group-mod">
				<span class="input-group-addon">Описание</span> <span><textarea class="form-control" name="description">${manufacturer.description}</textarea></span>
			</div>


			<div class="input-group input-group-mod">
				<span class="input-group-addon">Изображение 400x300</span> <span><input type="file" name="fileImage" class="form-control"></span>
			</div>
			
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Группа</span> <span> <select name="group" class="form-control">
						<option value="0">Без группы</option>
						<c:forEach var="group" items="${groupList}">
							<option value="${group.id}" <c:if test="${category.group.id eq group.id}">selected</c:if>  >${group.name}</option>
						</c:forEach>
				</select>
				</span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #1</span> <span><input type="text" name="supportFieldName1" class="form-control" value="${category.supportFieldName1}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #2</span> <span><input type="text" name="supportFieldName2" class="form-control" value="${category.supportFieldName2}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #3</span> <span><input type="text" name="supportFieldName3" class="form-control" value="${category.supportFieldName3}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #4</span> <span><input type="text" name="supportFieldName4" class="form-control" value="${category.supportFieldName4}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #5</span> <span><input type="text" name="supportFieldName5" class="form-control" value="${category.supportFieldName5}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #6</span> <span><input type="text" name="supportFieldName6" class="form-control" value="${category.supportFieldName6}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #7</span> <span><input type="text" name="supportFieldName7" class="form-control" value="${category.supportFieldName7}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #8</span> <span><input type="text" name="supportFieldName8" class="form-control" value="${category.supportFieldName8}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #9</span> <span><input type="text" name="supportFieldName9" class="form-control" value="${category.supportFieldName9}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название поля #10</span> <span><input type="text" name="supportFieldName10" class="form-control" value="${category.supportFieldName10}"></span>
			</div>



			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-success"></span>
			</div>
		</form>


	</div>
</div>