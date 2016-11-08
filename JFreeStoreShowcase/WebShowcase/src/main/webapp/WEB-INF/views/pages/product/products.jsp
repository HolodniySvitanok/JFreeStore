<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.holodniysvitanok.conf.WebModuleConfig"%>

<div class="container  container-mod">

	<div class="row">
		<div class="col-md-4 portfolio-item">
			<div class="dropdown button-mod">
				<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					${selectCategory.name} <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${manufacturer}&category=all&start=${start}&end=${end}&&order=asc&sort=${sort}">Все</a></li>
					<c:forEach var="cat" items="${categoryList}">
						<li><a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${manufacturer}&category=${cat.id}&start=${start}&end=${end}&&order=asc&sort=${sort}">${cat.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="dropdown button-mod">
				<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					${selectManufacturer.name} <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=all&category=${category}&start=${start}&end=${end}&&order=asc&sort=${sort}">Все</a></li>
					<c:forEach var="man" items="${manufacturerList}">
						<li><a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${man.id}&category=${category}&start=${start}&end=${end}&&order=asc&sort=${sort}">${man.name}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="col-md-4 portfolio-item">
			<div class="btn-group button-mod" role="group">
				<a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${manufacturer}&category=${category}&start=${start}&end=${end}&&order=${order}&sort=default" type="button" class="btn <c:if test="${'default' eq sort}">btn-primary</c:if> <c:if test="${'default' != sort}">btn-default</c:if>">Сортировка по списку</a> <a
					href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${manufacturer}&category=${category}&start=${start}&end=${end}&&order=${order}&sort=price" type="button" class="btn <c:if test="${'price' eq sort}">btn-primary</c:if> <c:if test="${'price' != sort}">btn-default</c:if>">Сортировка по цене</a>
			</div>
		</div>
		<div class="col-md-4 portfolio-item">
			<div class="btn-group button-mod" role="group">
				<a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${manufacturer}&category=${category}&start=${start}&end=${end}&&order=asc&sort=${sort}" type="button" class="btn <c:if test="${'asc' eq order}">btn-primary</c:if> <c:if test="${'asc' != order}">btn-default</c:if>">Упоряд. по возв.</a> <a
					href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${manufacturer}&category=${category}&start=${start}&end=${end}&&order=desc&sort=${sort}" type="button" class="btn <c:if test="${'desc' eq order}">btn-primary</c:if> <c:if test="${'desc' != order}">btn-default</c:if>">Упоряд. убыв.</a>
			</div>
		</div>
	</div>

	<br> <br>

	<div class="row">
		<c:forEach var="product" items="${products.items}" varStatus="status">

			<div class="col-md-3 portfolio-item">
				<a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCT%>?id=${product.id}"> <img class="img-responsive" src="http://placehold.it/700x400" alt="">
				</a>
                <h5 class="pull-right"><span id="price_${product.id}">${product.sellPrice}</span> <%= WebModuleConfig.CURRENCY %></h5>
                <h5>
					<a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCT%>?id=${product.id}">${product.category.name} ${product.manufacturer.name} ${product.supportField1} ${product.supportField2}</a>
				</h5>
            <%--<p>${product.sellPrice}	<%=WebModuleConfig.CURRENCY%></p>--%>
                <div class="row">
                    <div class="col-lg-7"></div>
                    <div class="col-lg-5">
                        <div class="input-group">
                            <input type="text" class="form-control " placeholder="0" id="count_${product.id}">
                            <span class="input-group-btn">
							<button class="btn  btn-success" type="button" onclick="basket('${pageContext.servletContext.contextPath}/<%= WebModuleConfig.ADD_TO_BASKET %>', ${product.id})"><span class="glyphicon glyphicon-shopping-cart"></span></button>
						</span>
                        </div><!-- /input-group -->
                    </div><!-- /.col-lg-6 -->
			    </div>
                <br>
            </div>


		<c:if test="${ (status.count % 4) == 0 }">
				</div><div class="row">
		</c:if> 

		</c:forEach>
	</div>

	<!-- Pagination -->
	<div class="row text-center">
		<div class="col-lg-12">
			<ul class="pagination">

				<c:forEach var="pageLine" items="${products.line}" varStatus="status">
					<li <c:if test="${(status.count-1) eq products.thisPage}">class="active"</c:if>><a href="${pageContext.servletContext.contextPath}/<%= WebModuleConfig.PRODUCTS %>?manufacturer=${manufacturer}&category=${category}&start=${pageLine.start}&end=${pageLine.stop}&&order=${order}&sort=${sort}">${status.count}</a></li>
				</c:forEach>

			</ul>
		</div>
	</div>
</div>