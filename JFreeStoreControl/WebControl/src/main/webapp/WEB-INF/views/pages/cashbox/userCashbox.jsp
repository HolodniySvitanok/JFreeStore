<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Ваш баланса</h3>
	</div>
	<div class="panel-body">



		<div class="input-group input-group-mod">
			<span class="input-group-addon">Процент</span> <span><input type="text" class="form-control" name="name" readonly required value="${userCashbox.percent} % "></span>
		</div>
		
		
			<div class="input-group input-group-mod">
				<span class="input-group-addon">Баланс </span> <span><input type="text" class="form-control" readonly value="${userCashbox.cash} <%= GlobalConfigProject.CURRENCY_NAME %>">
				</span> <span class="input-group-btn">

						<a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/<%= GlobalConfigProject.CASHBOX %>/<%= GlobalConfigProject.TAKE_CASHBOX %>/${type}" >
							снять
						</a>
				</span>
			</div>
	
	</div>
</div>
