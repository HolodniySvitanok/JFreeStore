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
		<h3 class="panel-title">Добавить пользователя</h3>
	</div>
	<div class="panel-body">

		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.ADD_USER %>" method="POST">

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Имя</span> <span><input type="text" class="form-control" name="name" placeholder="введите имя" required></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">E-mail</span> <span><input type="text" class="form-control" name="email" placeholder="введите e-mai" required></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Телефон</span> <span><input type="text" class="form-control" name="mobilePhone" placeholder="введите номер телефона"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Логин</span> <span><input type="text" class="form-control" name="login" placeholder="введите логин"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Пароль</span> <span><input type="password" class="form-control" name="password1" placeholder="введите пароль"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon"></span> <span><input type="password" class="form-control" name="password2" placeholder="введите пароль еще раз"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Уровень доступа</span> <span> <select name="accessLevel" class="form-control">
						<c:forEach var="al" begin="${accessLevel}" end="<%= GlobalConfigProject.MIN_ACCESS_LEVEL %>">
							<option value="${al}">${al}</option>
						</c:forEach>
				</select>
				</span>
			</div>

			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-default"></span>
			</div>
		</form>

	</div>
</div>