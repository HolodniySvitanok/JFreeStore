<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>





<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Найти заказ</h3>
	</div>

	<div class="panel-body">

		<form class="navbar-form navbar-center" role="search" action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.REVIEW_ORDER %>" method="get">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Найти заказ по ID" required name="id">
			</div>
			<button type="submit" class="btn btn-sm btn-primary">Найти</button>
		</form>
	</div>
</div>