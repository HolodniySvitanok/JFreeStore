<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>

<div class="navbar navbar-inverse  navbar-static-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/JFreeStore">JFreeStore</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">

				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Заказы <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.FIND_ORDER %>">Найти заказ</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ADD_ORDER %>">Добавить заказ</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ACTIVE_ORDER %>">Активные заказы</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.COMPLETE_ORDER %>">Завершенные заказы</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Товары <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="dropdown-header">Товар</li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.FIND_PRODUCT %>">Найти товар</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.SHOW_PRODUCT %>">Все товары</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.ADD_PRODUCT %>">Добавить товар</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Производитель</li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.FIND_MANUFACTURER %>">Найти производителя</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.SHOW_MANUFACTURER %>">Отобразить всех производителей</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.ADD_MANUFACTURER %>">Добавить производителя</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Категория</li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.FIND_CATEGORY %>">Найти категорию</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.SHOW_CATEGORY %>">Отобразить все категории</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.ADD_CATEGORY %>">Добавить категорию</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Группы</li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.GROUP %>/<%= GlobalConfigProject.CONTROL_GROUP %>">Управление группами</a></li>
					
					</ul>
				</li>

				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Пользователи <b class="caret"></b></a>
					<ul class="dropdown-menu">

						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.FIND_USER %>">Найти пользователя</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.SHOW_USER %>">Отобразить всех пользователей</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.ADD_USER %>">Добавить пользователя</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.INFO_USER %>">Информация</a></li>
					</ul></li>

				<li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Финансы <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.STATUS_CASHBOX %>">Касса</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.USER_CASHBOX %>">Ваш баланс</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.LOG_CASHBOX %>">Логи</a></li>
					</ul>
				</li>
				
			</ul>
			<p class="navbar-text navbar-right">
				<a class="navbar-link" href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.EXIT_USER %>">Выйти</a>
			</p>
			<p class="navbar-text navbar-right">
				Вошли как <a href="#" class="navbar-link">${user.name}</a>
			</p>
			<p class="navbar-text navbar-right">
				<c:if test="${not empty sessionScope.nameSelectOrder}">
					<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.REVIEW_ORDER %>?id=${sessionScope.selectOrderId}">Выбранный заказ: ${sessionScope.nameSelectOrder}</a>
				</c:if>
			</p>

		</div>
		<!--/.navbar-collapse -->
	</div>

</div>

