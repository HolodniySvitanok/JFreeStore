<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject" %>

<div class="left3">
	<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>"><span class="leftTitle">Товар</span></a>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.FIND_PRODUCT %>"><span class="leftSpan">Найти</span> товар</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.SHOW_PRODUCT %>"><span class="leftSpan">Отобразить</span> все товары</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.ADD_PRODUCT %>"><span class="leftSpan">Добавить</span> товар</a></div>
	
</div>

<div class="left3">
	<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>"><span class="leftTitle">Производитель</span></a>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.FIND_MANUFACTURER %>"><span class="leftSpan">Найти</span> производителя</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.SHOW_MANUFACTURER %>"><span class="leftSpan">Отобразить</span> всех производителей</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.MANUFACTURER %>/<%= GlobalConfigProject.ADD_MANUFACTURER %>"><span class="leftSpan">Добавить</span> производителя</a></div>
	
</div>

<div class="left3">
	<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>"><span class="leftTitle">Категория</span></a>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.FIND_CATEGORY %>"><span class="leftSpan">Найти</span> категорию</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.SHOW_CATEGORY %>"><span class="leftSpan">Отобразить</span> все категории</a></div>
	<div class="left2"><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.CATEGORY %>/<%= GlobalConfigProject.ADD_CATEGORY %>"><span class="leftSpan">Добавить</span> категорию</a></div>
	
</div>