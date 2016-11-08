<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Касса магазина</h3>
	</div>
	<div class="panel-body">


		<div class="input-group input-group-mod">
			<span class="input-group-addon">Касса работников</span> <span><input type="text" class="form-control" name="name" readonly required value="${sumCashbox.cash} <%= GlobalConfigProject.CURRENCY_NAME %>"></span>
		</div>

		<div class="input-group input-group-mod">
			<span class="input-group-addon">На закупку товара</span> <span><input type="text" class="form-control" readonly value="${mainCashbox.cash} <%= GlobalConfigProject.CURRENCY_NAME %>"></span> 
			<span class="input-group-btn"> 
				<a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.TAKE_CASHBOX %>/${type}">снять</a>
			</span>
		</div>
	</div>
</div>

