<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="en_US" scope="session"/>

<c:if test="${not empty message}">
	<div class="alert alert-danger">
		<strong>${message}</strong>
	</div>
</c:if>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Редактировать товар</h3>
	</div>
	<div class="panel-body">




		<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.SET_PRODUCT %>" enctype="multipart/form-data" method="POST"  class="curr-converter-on">

			<div class="input-group input-group-mod">
				<span class="input-group-addon">ID</span> 
				<span><input type="text" class="form-control" name="id" readonly value="${product.id}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена закупки в $</span>
				 <span><input type="text" class="form-control" name="purchaseUsdPrice" placeholder="введите число" required value="${product.purchaseUsdPrice}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена закупки в <%=GlobalConfigProject.CURRENCY_NAME%></span> 
				<span><input type="text" class="form-control" name="purchasePrice" placeholder="введите число" value="<fmt:formatNumber value="${product.calcPurchasePrice()}" pattern="###.##" groupingUsed="false"/>"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Курс на момент закупки</span> 
				<span><input type="text" class="form-control" name="purchaseExchange" value="${product.purchaseExchange}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена продажи в $</span> 
				<span><input type="text" class="form-control" name="sellUsdPrice" placeholder="введите число" required value="${product.sellUsdPrice}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Цена продажи в <%=GlobalConfigProject.CURRENCY_NAME%></span> 
				<span><input type="text" class="form-control" name="sellPrice" placeholder="введите число" value="<fmt:formatNumber groupingUsed="false" value="${product.calcSellPrice()}" pattern="###.##"/>"></span>
			</div>


			<div class="input-group input-group-mod">
				<span class="input-group-addon">Курс на момент продажи</span> 
				<span>
					<input type="text" class="form-control" name="sellExchange" value="${product.sellExchange}" id="sellExchange">
				</span> 
				<span class="input-group-btn">
					<a href="#" class="btn btn-primary" role="button" onclick="actualExchangerate('${pageContext.request.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.ACTUAL_EXCHANGE_RATE %>')">Актуальный курс</a>
				</span>
			
			</div>


			<div class="input-group input-group-mod">
				<span class="input-group-addon">Количество</span> <span><input type="text" class="form-control" name="count" placeholder="введите число" value="${product.count}"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Короткое описание</span> <span><textarea name="shortDescription" class="form-control">${product.shortDescription}</textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Главное описание</span> <span><textarea name="mainDescription" class="form-control">${product.mainDescription}</textarea></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Производитель</span> <span> <select name="manufacturer" class="form-control">
						<c:forEach var="manufacturer" items="${manufacturerList}">
							<option value="${manufacturer.id}" <c:if test="${product.manufacturer.id eq manufacturer.id}">selected</c:if>>${manufacturer.name}</option>
						</c:forEach>
				</select>
				</span>
			</div>



			<div class="input-group input-group-mod">
				<span class="input-group-addon">Показывать на ветрине</span> <span class="input-group-addon"><input class="form-control" type="checkbox" name="show" value="true" <c:if test="${product.show}">checked</c:if>></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Акция</span> <span class="input-group-addon"><input class="form-control" type="checkbox" name="stock" value="true" <c:if test="${product.stock}">checked</c:if>></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Новинка</span> <span class="input-group-addon"><input class="form-control" type="checkbox" name="novelty" value="true" <c:if test="${product.novelty}">checked</c:if>></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Карусель</span> <span class="input-group-addon"><input class="form-control" type="checkbox" name="carousel" value="true" <c:if test="${product.carousel}">checked</c:if>></span>
			</div>



			<div class="input-group input-group-mod">
				<span class="input-group-addon">Изображение товара 1 320x120</span> <span><input type="file" class="form-control" name="fileImage1"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Изображение товара 2 700x400</span> <span><input type="file" class="form-control " name="fileImage2"></span>
			</div>

			<div class="input-group input-group-mod">
				<span class="input-group-addon">Изображение товара 3 800x300</span> <span><input type="file" class="form-control " name="fileImage3"></span>
			</div>


			<c:if test="${not empty product.category.supportFieldName1}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName1}</span> <span><input type="text" class="form-control " name="field1" value="${product.supportField1}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName2}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName2}</span> <span><input type="text" class="form-control " name="field2" value="${product.supportField2}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName3}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName3}</span> <span><input type="text" class="form-control " name="field3" value="${product.supportField3}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName4}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName4}</span> <span><input type="text" class="form-control " name="field4" value="${product.supportField4}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName5}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName5}</span> <span><input type="text" class="form-control " name="field5" value="${product.supportField5}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName6}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName6}</span> <span><input type="text" class="form-control " name="field6" value="${product.supportField6}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName7}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName7}</span> <span><input type="text" class="form-control " name="field7" value="${product.supportField7}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName8}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName8}</span> <span><input type="text" class="form-control " name="field8" value="${product.supportField8}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName9}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName9}</span> <span><input type="text" class="form-control " name="field9" value="${product.supportField9}"></span>
				</div>
			</c:if>

			<c:if test="${not empty product.category.supportFieldName10}">
				<div class="input-group input-group-mod">
					<span class="input-group-addon">${product.category.supportFieldName10}</span> <span><input type="text" class="form-control " name="field10" value="${product.supportField10}"></span>
				</div>
			</c:if>

			<div class="input-group input-group-mod">
				<span><input type="submit" value="Сохранить" class="btn btn-success"></span>
			</div>



		</form>

	</div>
</div>