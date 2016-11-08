<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>


<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Добавить товар</h3>
	</div>
	<div class="panel-body">

		<c:if test="${showCategory}">

			<c:if test="${empty categoryList}">
				<div class="alert alert-danger">
					<strong>Товар невозможно добавить т.к. нету категории.</strong> Добавьте категорию.
				</div>
			</c:if>

			<c:if test="${not empty categoryList}">
				<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.ADD_PRODUCT %>" method="GET">

					<div class="input-group input-group-mod">
						<span class="input-group-addon">Выбери категорию</span> <span> <select name="category" class="form-control">
								<c:forEach var="category" items="${categoryList}">
									<option value="${category.id}">${category.name}</option>
								</c:forEach>
						</select>
						</span>

					</div>
					<input type="submit" value="Выбрать" class="btn btn-sm btn-primary">
				</form>
			</c:if>

		</c:if>

		<c:if test="${not showCategory}">

			<!-- Если нет ни одного производителя в бд то вывести сообщение отом, что надо его добавить -->
			<c:if test="${empty manufacturerList}">
				<div class="alert alert-danger">
					<strong>Добавьте производителя</strong>
				</div>
			</c:if>

			<c:if test="${not empty manufacturerList}">



						<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.ADD_PRODUCT %>" method="POST" enctype="multipart/form-data" class="curr-converter-on">
							<input type="hidden" name="category" value="${category.id}">

							<div class="input-group input-group-mod">
								<span class="input-group-addon">Цена закупки в $</span>
								<span><input type="text" class="form-control" name="purchaseUsdPrice" placeholder="введите число" required></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Цена закупки в <%=GlobalConfigProject.CURRENCY_NAME%></span>
								<span><input type="text"  class="form-control" name="purchasePrice" placeholder="введите число"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Курс на момент закупки</span>
								<span><input type="text"  name="purchaseExchange" class="form-control" placeholder="введите число" value="${rateCoefficient}"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Цена продажи в $</span>
								<span><input type="text" name="sellUsdPrice" class="form-control" placeholder="введите число" required></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Цена продажи в <%=GlobalConfigProject.CURRENCY_NAME%></span>
								<span><input type="text"  class="form-control" name="sellPrice" placeholder="введите число"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Курс на момент продажи</span>
								<span><input type="text" class="form-control"  name="sellExchange" placeholder="введите число" value="${rateCoefficient}"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Количество</span><span><input type="number" class="form-control" name="count" min="1"  placeholder="введите число" required></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Короткое описание</span><span><textarea name="shortDescription" class="form-control" cols="30" rows="7"></textarea></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Главное описание</span><span><textarea name="mainDescription" class="form-control" cols="30" rows="7"></textarea></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Производитель</span><span> <select name="manufacturer" class="form-control">
										<c:forEach var="manufacturer" items="${manufacturerList}">
											<option value="${manufacturer.id}">${manufacturer.name}</option>
										</c:forEach>
								</select>
								</span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Показывать на ветрине</span><span class="input-group-addon"><input class="form-control" type="checkbox" name="show" value="true"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Акция</span><span class="input-group-addon"><input class="form-control" type="checkbox" name="stock" value="true"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Новинка</span><span class="input-group-addon"><input class="form-control" type="checkbox" name="novelty" value="true"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Карусель</span><span class="input-group-addon"><input class="form-control" type="checkbox" name="carousel" value="false"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Изображение товара 1 320x150</span><span><input type="file" name="fileImage1" class="form-control"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Изображение товара 2 700x400</span><span><input type="file" name="fileImage2" class="form-control"></span>
							</div>
							<div class="input-group input-group-mod">
								<span class="input-group-addon">Изображение товара 3 800x300</span><span><input type="file" name="fileImage3" class="form-control"></span>
							</div>
							<c:if test="${not empty category.supportFieldName1}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName1}</span><span><input type="text" name="field1" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName2}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName2}</span><span><input type="text" name="field2" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName3}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName3}</span><span><input type="text" name="field3" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName4}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName4}</span><span><input type="text" name="field4" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName5}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName5}</span><span><input type="text" name="field5" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName6}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName6}</span><span><input type="text" name="field6" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName8}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName7}</span><span><input type="text" name="field7" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName7}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName8}</span><span><input type="text" name="field8" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName9}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName9}</span><span><input type="text" name="field9" class="form-control"></span>
								</div>
							</c:if>
							<c:if test="${not empty category.supportFieldName10}">
								<div class="input-group input-group-mod">
									<span class="input-group-addon">${category.supportFieldName10}</span><span><input type="text" name="field10" class="form-control"></span>
								</div>
							</c:if>
							<div class="input-group input-group-mod">
								<span><input type="submit" value="Сохранить" class="btn btn-success"></span>
							</div>
						</form>

			</c:if>
		</c:if>

	</div>
</div>

