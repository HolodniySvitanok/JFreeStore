<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Все товары</h3>
	</div>
	<div class="panel-body">


		<div class="list-group">

			<c:forEach var="category" items="${categoryList}">
				<a class="list-group-item" href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.SHOW_PRODUCT %>?category=${category.id}"><strong>${category.name} </strong></a>
			</c:forEach>

		</div>
	</div>
</div>