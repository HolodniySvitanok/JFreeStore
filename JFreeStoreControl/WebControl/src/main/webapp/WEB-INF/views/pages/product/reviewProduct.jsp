<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<fmt:setLocale value="en_US" scope="session"/>

<div class="page-header">
	<h3>${product.id} ${product.category.name} ${product.manufacturer.name} ${product.supportField1} ${product.supportField2}</h3>
	<div class="btn-group">
		<button type="button" class="btn btn-success dropdown-toggle btn-sm" data-toggle="dropdown">
			Действие <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu">
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.SET_PRODUCT %>?id=${product.id}" >Редактировать</a></li>
			<li><a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.DEL_PRODUCT %>?id=${product.id}" >Удалить</a></li>
		</ul>
	</div>
</div>




<div class="panel panel-primary">
	<div class="panel-heading">
		<strong>Просмотреть товар</strong>
	</div>
	<div class="panel-body">
		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ADD_PRODUCT_IN_ORDER %>" method="POST">
			<c:if test="${not empty sessionScope.selectOrderId}">
				<div class="input-group">
					<input type="hidden" name="id" value="${product.id}"> <input type="text" min="1" class="form-control" name="count" placeholder="количество"> <span class="input-group-btn">
						<button class="btn btn-default" type="button">Добавить в заказ</button>
					</span>
				</div>

			</c:if>
			<br> <br>
			<div class="row">
				<c:if test="${not empty product.urlImage1}">
					<div class="col-xs-12 col-md-4">
						<a href="#" class="thumbnail"> <img class="img-responsive" src="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.IMAGE_BOX_URL %>/${product.urlImage1}">
						</a>
					</div>
				</c:if>
				<c:if test="${not empty product.urlImage2}">
					<div class="col-xs-12 col-md-4">
						<a href="#" class="thumbnail"> <img class="img-responsive" src="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.IMAGE_BOX_URL %>/${product.urlImage2}">
						</a>
					</div>
				</c:if>
				<c:if test="${not empty product.urlImage3}">
					<div class="col-xs-12 col-md-4">
						<a href="#" class="thumbnail"> <img class="img-responsive" src="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.IMAGE_BOX_URL %>/${product.urlImage3}">
						</a>
					</div>
				</c:if>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">ID</span> <span><input type="text" class="form-control" value="${product.id}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена закупки в $</span> <span><input type="text" class="form-control" value="${product.purchaseUsdPrice}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена закупки в <%=GlobalConfigProject.CURRENCY_NAME%></span> <span><input type="text" class="form-control" value="<fmt:formatNumber value="${product.calcPurchasePrice()}" pattern="###,##"/>" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Курс на момент закупки</span> <span><input type="text" class="form-control" value="${product.purchaseExchange}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена продажи в $</span> <span><input type="text" class="form-control" value="${product.sellUsdPrice}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена продажи в <%=GlobalConfigProject.CURRENCY_NAME%></span> <span><input type="text" class="form-control" value="<fmt:formatNumber value="${product.calcSellPrice()}" pattern="###.##"/>" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Курс на момент продажи</span> <span><input type="text" class="form-control" value="${product.sellExchange}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Количество</span> <span><input type="text" class="form-control" value="${product.count}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Рейтинг</span> <span><input type="text" class="form-control" value="${product.rate}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Категория</span> <span><input type="text" class="form-control" value="${product.category.name}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Производитель</span> <span><input type="text" class="form-control" value="${product.manufacturer.name}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Короткое описание</span> <span><textarea readonly class="form-control">${product.shortDescription}</textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Главное описание</span> <span><textarea readonly class="form-control">${product.mainDescription}</textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Показывать</span> <span><input type="text" class="form-control" value="${product.show}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Акция</span> <span><input type="text" class="form-control" value="${product.stock}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Новинка</span> <span><input type="text" class="form-control" value="${product.novelty}" readonly></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Карусель</span> <span><input type="text" class="form-control" value="${product.carousel}" readonly></span>
			</div>

			<c:if test="${not empty product.category.supportFieldName1}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName1}</span> <span><input type="text" class="form-control" value="${product.supportField1}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName2}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName2}</span> <span><input type="text" class="form-control" value="${product.supportField2}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName3}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName3}</span> <span><input type="text" class="form-control" value="${product.supportField3}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName4}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName4}</span> <span><input type="text" class="form-control" value="${product.supportField4}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName5}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName5}</span> <span><input type="text" class="form-control" value="${product.supportField5}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName6}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName6}</span> <span><input type="text" class="form-control" value="${product.supportField6}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName7}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName7}</span> <span><input type="text" class="form-control" value="${product.supportField7}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName8}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName8}</span> <span><input type="text" class="form-control" value="${product.supportField8}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName9}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName9}</span> <span><input type="text" class="form-control" value="${product.supportField9}" readonly></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName10}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName10}</span> <span><input type="text" class="form-control" value="${product.supportField10}" readonly></span>
				</div>
			</c:if>
		</form>

	</div>
</div>
