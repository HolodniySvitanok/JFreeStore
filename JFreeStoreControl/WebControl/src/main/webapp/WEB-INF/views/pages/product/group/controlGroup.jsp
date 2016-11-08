<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>


<div class="panel panel-primary">
	<div class="panel-heading">Добавить группу</div>
	<div class="panel-body">
		<form method="POST" action="">
			<div class="input-group">
				<span class="input-group-addon">Название группы</span>
				 <input type="text" name="name" class="form-control" required placeholder="введите название"> 
				 <span class="input-group-btn">
				 <input type="submit" value="Добавить" class="btn btn-primary">
				 </span>
			</div>
		</form>
	</div>
</div>


<div class="panel panel-primary">
	<div class="panel-heading">Все группы</div>
	<div class="panel-body">

		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.GROUP %>/<%= GlobalConfigProject.UPDATE_GROUP %>" method="POST">
			<table class="table">
				<thead>
					<tr>
						<td>ID</td>
						<td>Название</td>
						<td>Удалить</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="group" items="${groupList}">
						<input type="hidden" value="${group.id}" name="id">
						<tr>
							<td>${group.id}</td>
							<td><input type="text" value="${group.name}" name="name"></td>
							<td><input type="checkbox" name="delete" value="${group.id}"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit" value="Обновить">
		</form>
	</div>
</div>