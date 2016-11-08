<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta charset="utf-8">
<meta http-equiv="refresh" content="600">

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/logo-nav.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
 <link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/mod.css" rel="stylesheet">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="main" />
	<tiles:insertAttribute name="footer" />


	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/basket.js"></script>
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>
</html>

