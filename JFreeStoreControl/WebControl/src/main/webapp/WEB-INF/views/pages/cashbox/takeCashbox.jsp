<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>

<c:if test="${not empty message}">

	<div class="alert alert-danger">${message}</div>

</c:if>




<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Снять средства</h3>
	</div>
	<div class="panel-body">

		<form class="navbar-form navbar-center" role="search" action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.TAKE_CASHBOX %>" method="POST">
			<input type="hidden" value="${cashbox.id}" name="id">
			<input type="hidden" value="${type}" name="type">
			<h4>
				Доступный баланс ${cashbox.cash}
				<%=GlobalConfigProject.CURRENCY_NAME%></h4>

			<div class="form-group">
				<input type="number" min="0" max="${cashbox.cash}" class="form-control" placeholder="Введите сумму" style="width: 230px" required name="summ">
			</div>

			<button type="submit" class="btn btn-sm btn-primary">Снять</button>

		</form>


	</div>
</div>