<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject" %>

<div class="left3">
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.FIND_USER %>">Найти пользователя</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.SHOW_USER %>">Отобразить всех пользователей</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.ADD_USER %>">Добавить пользователя</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.USER %>/<%= GlobalConfigProject.INFO_USER %>">Информация</a></div>
</div>

