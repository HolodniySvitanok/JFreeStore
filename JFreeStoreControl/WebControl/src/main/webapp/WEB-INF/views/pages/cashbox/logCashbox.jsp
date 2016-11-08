<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Логи</h3>
	</div>
	<div class="panel-body">

		<ul class="nav nav-pills">
			<li <c:if test="${message eq 'main'}">class="active"</c:if>>
				<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.LOG_CASHBOX %>?select=main">Касса закупки</a>
			</li>
			<li <c:if test="${message eq 'you'}">class="active"</c:if>>
				<a	href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.LOG_CASHBOX %>?select=you">Твои</a>
			</li>
		</ul>

		<div class="table-responsive">
			<table class="table  table-hover">
				<thead>
					<tr>
						<td>ID</td>
						<td>С счета (ID)</td>
						<td>С заказ (ID)</td>
						<td>Сумма</td>
						<td>Дата</td>
						<td>Описание</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="log" items="${logList}" varStatus="loopCounter">
						<tr <c:if test="${ (loopCounter.count % 2) != 0  }"> class="info"</c:if> <c:if test="${(loopCounter.count % 2) == 0}">  class="active"</c:if>>
							<td>${log.id}</td>
							<td>${log.fromCashbox.id}</td>
							<td>${log.fromOrder.id}</td>
							<td>${log.sum}</td>
							<td><fmt:formatDate value="${log.timestamp}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td>${log.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>