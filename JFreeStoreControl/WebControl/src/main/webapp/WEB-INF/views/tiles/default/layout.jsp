<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<!DOCTYPE html>
<html>
<head>
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="${pageContext.request.contextPath}/resources/css/starter-template.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/sticky-footer.css" rel="stylesheet">


<meta http-equiv="refresh" content="600">

</head>
<body>

	<tiles:insertAttribute name="header" />
	<%-- <tiles:insertAttribute name="left" /> --%>



	<div class="container-fluid">
		<div class="starter-template">
			<div class="row">
				<!-- <div class="col-lg-12"> -->
				<ul class="pager">
					<li class="previous"><a href="#" id="previousPage" onclick="previousPage('${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PREVIOUS_PAGE%>')">&larr; Назад</a></li>
				</ul>

				<tiles:insertAttribute name="main" />
				<!-- </div> -->
			</div>
		</div>
	</div>
	<!-- /.container -->

	<tiles:insertAttribute name="footer" />



	<script src="${pageContext.request.contextPath}/resources/js/md5.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/form.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/conv.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/previousPage.js"></script>
</body>
</html>

