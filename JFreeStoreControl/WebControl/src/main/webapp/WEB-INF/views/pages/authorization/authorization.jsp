<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>

<div id="error"></div>


<img src="${pageContext.servletContext.contextPath}/resources/img/fblfbb.gif">
<div class="container">
	<form class="form-signin" role="form">
		<h2 class="form-signin-heading">Please sign in</h2>
		<input type="text" class="form-control" placeholder="Login" required autofocus id="login"> 
		<input type="password" class="form-control" placeholder="Password" required id="password"> 
		<input type="button" class="btn btn-lg btn-primary btn-block"
			onclick="sendUser('${pageContext.request.contextPath}','${pageContext.request.contextPath}/<%= GlobalConfigProject.AUTHORIZATION %>')" value="Sign in">
	</form>

</div>