<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>




<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Все пользователи</h3>
	</div>
	<div class="panel-body">

		<div id="tableShowProduct">
			<div class="table-responsive">

				<table class="table table-hover">
					<thead>
						<tr>
							<td><b>ID</b></td>
							<td><b>Имя</b></td>
							<td><b>Логин</b></td>
							<td><b>Уровень<br>доступа
							</b></td>
							<td><b>Действие</b></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList}"  varStatus="loopCounter">
							<tr  <c:if test="${ (loopCounter.count % 2) != 0  }"> class="info"</c:if> <c:if test="${(loopCounter.count % 2) == 0}">  class="active"</c:if>>
								<td>${user.id}</td>
								<td>${user.name}</td>
								<td>${user.login}</td>
								<td>${user.accessLevel}</td>
								<td>
								<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.REVIEW_USER %>?id=${user.id}"><span class="glyphicon glyphicon-info-sign"></span></a>
								<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.SET_USER %>?id=${user.id}"><span class="glyphicon glyphicon-pencil"></span></a> 
								<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.DEL_USER %>?id=${user.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>
</div>