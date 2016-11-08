<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="page-header">
		<h3>Завершенный заказ</h3>
	<div  class="btn-group">
		<button type="button" class="btn btn-success dropdown-toggle btn-sm " data-toggle="dropdown">
			Действие <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.RETURN_ACTIVE_ORDER %>?id=${order.id}">Восстановить заказ</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.SET_ORDER %>?id=${order.id}">Редактировать</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.DEL_ORDER %>?id=${order.id}">Удалить</a></li>
		</ul>
	</div>
</div>






<div class="row">
	<div class="col-md-6">

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Информация</h3>
			</div>

			<div class="panel-body">


				<div class="input-group input-group-mod">
					<span class="input-group-addon">ID</span><input class="form-control" type="text" readonly value=" ${order.id}">
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Пользователь</span><input class="form-control" type="text" readonly value="${order.user.name}">
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Название</span><input class="form-control" type="text" readonly value=" ${order.name}">
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Описание</span>
					<textarea class="form-control" name="description" readonly>${order.description}</textarea>
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Активный</span><input class="form-control" type="text" readonly value="  ${order.active}">
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Новый</span><input class="form-control" type="text" readonly value="  ${order.newOrder}">
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Дата</span>
					<input class="form-control" type="text" readonly value="<fmt:formatDate value="${order.timestamp}" pattern="yyyy-MM-dd HH:mm" />">
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Сумма</span><input class="form-control" type="text" readonly value=" <fmt:formatNumber value="${order.getSumOrder()}" pattern="###.##"/>грн">
				</div>

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Заработок с заказа</span><input class="form-control" type="text" readonly value="<fmt:formatNumber value="${order.getDifferenceSumOrder()}" pattern="###.##"/>грн">
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-6">

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">Расходы</h3>
			</div>

			<div class="panel-body">

				<div class="input-group input-group-mod">
					<span class="input-group-addon">Расходы</span><input class="form-control" type="text" readonly value="${order.expenses}">
				</div>
				<div class="input-group input-group-mod">
					<span class="input-group-addon">Комментарий</span>
					<textarea class="form-control" name="commentExpenses" readonly>${order.commentExpenses}</textarea>
				</div>
			</div>
		</div>
	</div>
</div>


<br>
<br>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Товары</h3>
	</div>

	<div class="panel-body">

		<div class="table-responsive">
			<table class="table  table-hover">
				<thead>
					<tr>
						<td>ID</td>
						<td>Товар</td>
						<td>Кол-во</td>
						<td>Скидка</td>
						<td>Сумма</td>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="orderDetails" items="${orderDetailsList}" varStatus="loopCounter">
						<tr <c:if test="${ (loopCounter.count % 2) != 0  }"> class="info"</c:if> <c:if test="${(loopCounter.count % 2) == 0}">  class="active"</c:if>>
							<td>${orderDetails.id}</td>
							<td><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.REVIEW_CATEGORY %>?id=${orderDetails.product.category.id}">${orderDetails.product.category.name}</a> <a
								href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.REVIEW_MANUFACTURER %>?id=${orderDetails.product.manufacturer.id}">${orderDetails.product.manufacturer.name}</a> <a
								href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.REVIEW_PRODUCT %>?id=${orderDetails.product.id}">${orderDetails.product.supportField1} ${orderDetails.product.supportField2} (${orderDetails.product.id})</a></td>
							<td>${orderDetails.countProduct}</td>
							<td>${orderDetails.kPrice}</td>
							<td><fmt:formatNumber value="${orderDetails.sumPrice}" pattern="###.##" />грн</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>
</div>