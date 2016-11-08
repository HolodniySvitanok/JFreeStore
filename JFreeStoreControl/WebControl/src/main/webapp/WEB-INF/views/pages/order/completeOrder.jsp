<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Завершенные заказы</h3>
	</div>
	<div class="panel-body">


		<form action="" method="POST">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<td>ID</td>
						<td>Название</td>
						<td>Пользователь</td>
						<td>Цена</td>
						<td>Заработок</td>
						<td>Дата</td>
						<td>Действие</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderList}" varStatus="loopCounter">
						<tr <c:if test="${ (loopCounter.count % 2) != 0  }"> class="info"</c:if> <c:if test="${(loopCounter.count % 2) == 0}">  class="active"</c:if>>
							<td>${order.id}</td>
							<td>${order.name}</td>
							<td><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.REVIEW_USER %>?id=${order.user.id}">${order.user.login}</a></td>
							<td><fmt:formatNumber value="${order.getSumOrder()}" pattern="###.##" /> грн</td>
							<td><fmt:formatNumber value="${order.getDifferenceSumOrder()}" pattern="###.##" /> грн</td>
							<%-- <td>${order.timestamp}</td> --%>
							<td><fmt:formatDate value="${order.timestamp}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.REVIEW_COMPLETE_ORDER %>?id=${order.id}"><span
									class="glyphicon glyphicon-info-sign"></span></a> <a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.SET_ORDER %>?id=${order.id}"><span
									class="glyphicon glyphicon-pencil"></span></a> <a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.RETURN_ACTIVE_ORDER %>?id=${order.id}"><span
									class="glyphicon glyphicon-repeat"></span></a> <a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.DEL_ORDER %>?id=${order.id}"><span
									class="glyphicon glyphicon-trash"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</form>
	</div>
</div>

