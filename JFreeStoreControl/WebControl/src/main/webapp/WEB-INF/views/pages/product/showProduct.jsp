<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.holodniysvitanok.service.GlobalConfigProject"%>
<fmt:setLocale value="en_US" scope="session" />

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">${categoryName}</h3>
	</div>
	<div class="panel-body">



		<div id="tableShowProduct">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr class="trTable">
							<td>ID</td>
							<td>Категория</td>
							<td>Производитель</td>

							<c:if test="${not empty field1}">
								<td>${field1}</td>
							</c:if>

							<c:if test="${not empty field2}">
								<td>${field2}</td>
							</c:if>

							<td>Цена<br>зак. $
							</td>
							<td>Цена<br>прод. $
							</td>
							<td>Курс<br>пок.
							</td>
							<td>Цена<br>зак. <%=GlobalConfigProject.CURRENCY_NAME%></td>
							<td>Цена<br>прод. <%=GlobalConfigProject.CURRENCY_NAME%></td>
							<td>Курс<br>прод.
							</td>

							<td>Кол.</td>
							<td>Действие</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${productList}" varStatus="loopCounter">
							<tr <c:if test="${ (loopCounter.count % 2) != 0  }"> class="info"</c:if> <c:if test="${(loopCounter.count % 2) == 0}">  class="active"</c:if>>
								<td>${product.id}</td>
								<td>${product.category.name}</td>
								<td>${product.manufacturer.name}</td>


								<c:if test="${not empty field1}">
									<td>${product.supportField1}</td>
								</c:if>
								<c:if test="${not empty field1}">
									<td>${product.supportField2}</td>
								</c:if>

								<td>${product.purchaseUsdPrice}</td>
								<td>${product.sellUsdPrice}</td>
								<td>${product.purchaseExchange}</td>

								<td><fmt:formatNumber value="${product.calcPurchasePrice()}" pattern="###.##" /></td>
								<td><fmt:formatNumber value="${product.calcSellPrice()}" pattern="###.##" /></td>
								<td>${product.sellExchange}</td>

								<td>${product.count}</td>
								<td style="width: 250px">

									<div class="row">
										<div class="col-md-4 group-mod">
											<a href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.REVIEW_PRODUCT %>?id=${product.id}"><span class="glyphicon glyphicon-info-sign"></span></a> <a
												href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.SET_PRODUCT %>?id=${product.id}"><span class="glyphicon glyphicon-pencil"></span></a> <a
												href="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.PRODUCT %>/<%= GlobalConfigProject.DEL_PRODUCT %>?id=${product.id}"><span class="glyphicon glyphicon-trash"></span></a>
										</div>

										<div class="col-md-5 group-mod  hidden-xs">

											<div style="width: 130px;">
												<form action="${pageContext.servletContext.contextPath}/<%= GlobalConfigProject.ORDER %>/<%= GlobalConfigProject.ADD_PRODUCT_IN_ORDER %>" method="POST">
													<div class="input-group ">
														<input type="hidden" name="id" value="${product.id}"> <input type="number" min="1" class="form-control" name="count" placeholder="кол."> <span class="input-group-btn">
															<button class="btn btn-default " type="submit">add</button>
														</span>
													</div>
												</form>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>