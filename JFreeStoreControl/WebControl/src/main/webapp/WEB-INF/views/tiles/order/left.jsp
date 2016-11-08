<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject" %>


<div class="left3">
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.FIND_ORDER %>">Найти заказ</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ADD_ORDER %>">Добавить заказ</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ACTIVE_ORDER %>">Активные заказы</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.COMPLETE_ORDER %>">Завершенные заказы</a></div>
	
</div>