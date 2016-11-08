<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<div>Количесвто активных сессий ${userList.size()}</div>
<c:forEach var="user" items="${userList}">
	<span>${user.id} ${user.name}</span>
</c:forEach>