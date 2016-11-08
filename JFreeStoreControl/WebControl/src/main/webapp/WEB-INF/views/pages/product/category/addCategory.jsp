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
		<h3 class="panel-title">Добавить категорию</h3>
	</div>
	<div class="panel-body">


		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.ADD_CATEGORY %>" method="POST" enctype="multipart/form-data">


			<div class="input-group input-group-mod">
				<span class="input-group-addon">Название категории</span> <span><input type="text" class="form-control" name="name" placeholder="введите строку" required></span>
			</div>
			
			<div class="input-group input-group-mod">
				<span class="input-group-addon">URL категории</span> <span><input type="text" class="form-control" name="url" placeholder="введите строку" required></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Описание</span> <span><textarea class="form-control" name="description" placeholder="введите строку"></textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Изображение 400x300</span> <span><input type="file" class="form-control" name="fileImage"></span>
			</div>
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Группа</span> <span> <select name="group" class="form-control">
						<option value="0">Без группы</option>
						<c:forEach var="group" items="${groupList}">
							<option value="${group.id}">${group.name}</option>
						</c:forEach>
				</select>
				</span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #1</span> <span><input type="text" class="form-control" name="supportFieldName1" placeholder="введите строку"></span>
			</div>



			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #2</span> <span><input type="text" class="form-control" name="supportFieldName2" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #3</span> <span><input type="text" class="form-control" name="supportFieldName3" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #4</span> <span><input type="text" class="form-control" name="supportFieldName4" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #5</span> <span><input type="text" class="form-control" name="supportFieldName5" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #6</span> <span><input type="text" class="form-control" name="supportFieldName6" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #7</span> <span><input type="text" class="form-control" name="supportFieldName7" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #8</span> <span><input type="text" class="form-control" name="supportFieldName8" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #9</span> <span><input type="text" class="form-control" name="supportFieldName9" placeholder="введите строку"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Дополнительно поле #10</span> <span><input type="text" class="form-control" name="supportFieldName10" placeholder="введите строку"></span>
			</div>




			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-default"></span>
			</div>

		</form>
	</div>
</div>