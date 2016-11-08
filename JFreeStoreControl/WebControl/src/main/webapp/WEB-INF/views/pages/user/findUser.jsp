<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Найти пользователя</h3>
	</div>
	<div class="panel-body">


		<form class="navbar-form navbar-center" role="search" action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.REVIEW_USER %>" method="GET">
			<div class="form-group"><input class="form-control"  type="text" name="id" id="id" required placeholder="Найти пользователя по ID"></div> 
			<input type="submit" value="Найти" class="btn btn-sm btn-primary"> 
		</form>

	</div>
</div>