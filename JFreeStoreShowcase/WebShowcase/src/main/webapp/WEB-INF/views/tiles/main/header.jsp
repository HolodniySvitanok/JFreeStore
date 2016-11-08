<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.holodniysvitanok.conf.WebModuleConfig" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top navbar-mod" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
                <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}">
                <img src="${pageContext.servletContext.contextPath}/resources/img/logo.gif" alt="">
            </a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="${pageContext.servletContext.contextPath}">Главная</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                        aria-haspopup="true" aria-expanded="false">Категории <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach var="category" items="${categoryList}">
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS%>?manufacturer=all&category=${category.id}&start=0&end=11&&order=asc&sort=default">${category.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                        aria-haspopup="true" aria-expanded="false">Производители <span
                        class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <c:forEach var="manufacturer" items="${categoryManufacturer}">
                            <li>
                                <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS%>?manufacturer=${manufacturer.id}&category=all&start=0&end=11&&order=asc&sort=default">${manufacturer.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>

                <li><a href="#">Доставка</a></li>
                <li><a href="#">Контакты</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li style="padding-right: 20px; color: #cccccc"><a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.SHOW_BASKET %>">
                    <%--<h6><span class="glyphicon  glyphicon-apple"> : <span id="countPr">${basketStatus.countProducts}</span></h6>--%>
                    <span class="glyphicon glyphicon-shopping-cart"> <span id="totalPr">${basketStatus.totalPrice}</span> <%= WebModuleConfig.CURRENCY %>
                    </a>
                </li>
                <li><a href="#">${sessionScope.get("user").name}</a></li>
                <li><a href="#"> Войти</a></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>


<c:if test="${not empty globalMessage}">
    <div class="container container-mod">
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> <span class="sr-only">Error:</span>
                    ${globalMessage}
                </div>

            </div>
        </div>
    </div>
</c:if>