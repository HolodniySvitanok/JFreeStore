<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Активные заказы</h3>
	</div>
	<div class="panel-body">


		<ul class="nav nav-pills">
			<li <c:if test="${message eq 'all'}">class="active"</c:if>><a
				href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ACTIVE_ORDER %>?active=all">Все</a></li>
			<li <c:if test="${message eq 'you'}">class="active"</c:if>><a
				href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ACTIVE_ORDER %>?active=you">Твои</a></li>
			<li <c:if test="${message eq 'new'}">class="active"</c:if>><a
				href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ACTIVE_ORDER %>?active=new">Новые</a></li>
		</ul>


		<form action="" method="POST">
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<td><b>ID</b></td>
						<td><b>Название</b></td>
						<td><b>Пользователь</b></td>
						<td><b>Активный</b></td>
						<td><b>Новый</b></td>
						<td><b>Дата</b></td>
						<td><b>Цена</b></td>
						<td><b>Заработок</b></td>
						<td><b>Действие</b></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderList}" varStatus="loopCounter">
						<tr <c:if test="${ (loopCounter.count % 2) != 0  }"> class="info"</c:if> <c:if test="${(loopCounter.count % 2) == 0}">  class="active"</c:if> >
							<td>${order.id}</td>
							<td>${order.name}</td>
							<td><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.REVIEW_USER %>?id=${order.user.id}">${order.user.login}</a></td>
							<td>${order.active}</td>
							<td>${order.newOrder}</td>
							<%-- <td>${order.timestamp}</td> --%>
							<td><fmt:formatDate value="${order.timestamp}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td><fmt:formatNumber value="${order.getSumOrder()}" pattern="###.##" /> грн</td>
							<td><fmt:formatNumber value="${order.getDifferenceSumOrder()}" pattern="###.##" /> грн</td>
							<td>
								<!-- выбрать --> 
								<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.SET_SELECT_ORDER %>?id=${order.id}"><span class="glyphicon glyphicon-paperclip"></span></a> 
								<!-- просмотреть --> 
								<a	href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.REVIEW_ORDER %>?id=${order.id}"><span class="glyphicon glyphicon-info-sign"></span></a>
								<!-- завершить --> 
								<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.CLOSE_ORDER %>?id=${order.id}"><span class="glyphicon glyphicon-ok"></span></a> 
								<!-- редактировать --> 
								<a	href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.SET_ORDER %>?id=${order.id}"><span class="glyphicon glyphicon-pencil"></span></a> 
								<!-- удалить -->
								<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.DEL_ORDER %>?id=${order.id}"><span class="glyphicon glyphicon-trash"></span></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</form>
	</div>
</div>
