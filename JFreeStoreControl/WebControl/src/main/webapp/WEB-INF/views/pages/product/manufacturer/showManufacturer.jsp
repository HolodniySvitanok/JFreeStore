<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Все производители</h3>
	</div>
	<div class="panel-body">

		<div class="list-group">

			<c:forEach var="manufacturer" items="${manufacturerList}">
				<a class="list-group-item" href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.REVIEW_MANUFACTURER %>?id=${manufacturer.id}">
					<strong>${manufacturer.id}  ${manufacturer.name}  ${manufacturer.country} </strong>
				</a>
			</c:forEach>
		</div>
	</div>
</div>