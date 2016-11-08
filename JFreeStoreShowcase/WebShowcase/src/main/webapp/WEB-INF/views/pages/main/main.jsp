<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.holodniysvitanok.conf.WebModuleConfig" %>

<!-- Page Content -->
<div class="container  container-mod">
    <div class="row">

        <div class="col-lg-12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <c:forEach var="carousel" items="${cachedCarouselProducts}" varStatus="status">
                        <li data-target="#carousel-example-generic" data-slide-to="${status.count}" <c:if
                                test="${status.count eq 1}"> class="active"</c:if>></li>
                    </c:forEach>
                </ol>
                <div class="carousel-inner">
                    <c:forEach var="carousel" items="${cachedCarouselProducts}" varStatus="status">
                        <div class="item <c:if test="${status.count eq 1}">active</c:if>">
                            <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCT%>?id=${carousel.id}">
                                <img class="slide-image"
                                     src="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.IMAGE_BOX_URL%>/${carousel.urlImage3}"
                                     alt=""
                                ></a>
                        </div>
                    </c:forEach>
                </div>
                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev"> <span
                        class="glyphicon glyphicon-chevron-left"></span></a>
                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next"> <span
                        class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>

    </div>
    <div class="row">
        <hr>
        <div class="col-lg-12">
            <div class="well text-center"><h3>Случайные товары</h3></div>
        </div>
        <!-- /.col-lg-12 -->
    </div>


    <div class="row">

        <div class="col-lg-12 ">
            <div class="row mod">
                <c:forEach var="randomProduct" items="${cachedRandomProducts}" varStatus="status">
                    <div class="col-md-4 portfolio-item">
                        <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCT%>?id=${randomProduct.id}">
                            <img class="img-responsive" src="http://placehold.it/700x400" alt="">
                        </a>

                        <h4 class="pull-right"><span id="price_${randomProduct.id}">${randomProduct.sellPrice}</span> <%= WebModuleConfig.CURRENCY %>
                        </h4>
                        <h4>
                            <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCT %>?id=${randomProduct.id}">${randomProduct.category.name} ${randomProduct.manufacturer.name}</a>
                        </h4>
                        <div class="row">
                            <div class="col-lg-8"></div>
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="0" id="count_${randomProduct.id}">
                                    <span class="input-group-btn">
                                            <button class="btn btn-success" type="button" onclick="basket('${pageContext.servletContext.contextPath}/<%= WebModuleConfig.ADD_TO_BASKET %>', ${randomProduct.id})"><span class="glyphicon glyphicon-shopping-cart"></span></button>
                                    </span>
                                </div><!-- /input-group -->
                            </div><!-- /.col-lg-6 -->
                        </div>
                    </div>

                    <c:if test="${ (status.count % 3) == 0 }">
                            </div><div class="row">
                    </c:if>

                </c:forEach>
            </div>
        </div>

    </div>
    <a id="category" style="display: block; margin-bottom: 90px; margin-top: -70px"></a>
    <div class="row">
        <div class="col-lg-12">
            <div class="well text-center"><h3>Категории товара</h3></div>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <hr>

    <div class="row">
        <c:forEach var="category" items="${cachedCategory.items}">
            <div class="col-md-4 portfolio-item">
                <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS%>?manufacturer=all&category=${category.id}&start=0&end=11&&order=asc&sort=default">
                    <img class="img-responsive" src="http://placehold.it/750x450"
                         alt=""> <%-- 				<a href="#"> <img class="img-responsive" src="${pageContext.servletContext.contextPath}/resources/${category.urlImage}" alt=""> --%>
                </a>
                <h4>${category.name}</h4>
            </div>
        </c:forEach>
    </div>

    <!-- Pagination -->
    <div class="row text-center">
        <div class="col-lg-12">
            <ul class="pagination">

                <c:forEach var="pageLine" items="${cachedCategory.line}" varStatus="status">
                    <li <c:if test="${(status.count-1) eq cachedCategory.thisPage}">class="active"</c:if>><a
                            href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.MAIN %>?scat=${pageLine.start}&ecat=${pageLine.stop}&sman=${sman}&eman=${eman}&#category">${status.count}</a>
                    </li>
                </c:forEach>

            </ul>
        </div>
    </div>
    <!-- /.row -->
    <a id="manufacturer" style="display: block; margin-bottom: 90px; margin-top: -70px"></a>
    <hr>

    <div class="row">
        <div class="col-lg-12">
            <div class="well text-center"><h3>Производители</h3></div>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <hr>

    <div class="row">
        <c:forEach var="manufacturer" items="${cachedManufacturers.items}">
            <div class="col-md-4 portfolio-item">
                <a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS%>?manufacturer=${manufacturer.id}&category=all&start=0&end=11&&order=asc&sort=default">
                    <img class="img-responsive" src="http://placehold.it/750x450"
                         alt=""> <%-- 				<a href="#"> <img class="img-responsive" src="${pageContext.servletContext.contextPath}/resources/${category.urlImage}" alt=""> --%>
                </a>
                <h4>${manufacturer.name}</h4>
            </div>
        </c:forEach>
    </div>

    <!-- Pagination -->
    <div class="row text-center">
        <div class="col-lg-12">
            <ul class="pagination">
                <c:forEach var="pageLine" items="${cachedManufacturers.line}" varStatus="status">
                    <li <c:if test="${(status.count-1) eq cachedManufacturers.thisPage}">class="active"</c:if>><a
                            href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.MAIN %>?sman=${pageLine.start}&eman=${pageLine.stop}&scat=${scat}&ecat=${ecat}&#manufacturer">${status.count}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- /.row -->


</div>
<!-- /.container -->