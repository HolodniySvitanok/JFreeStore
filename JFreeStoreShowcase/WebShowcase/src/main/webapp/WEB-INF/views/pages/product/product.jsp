<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.holodniysvitanok.conf.WebModuleConfig"%>



<div class="container container-mod">
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="thumbnail">
				<img class="img-responsive" src="http://placehold.it/800x300" alt="">
				<div class="caption-full">
					<h4 class="pull-right">${product.sellPrice} <%= WebModuleConfig.CURRENCY %></h4>
					<h4>
						<a href="#">${product.category.name} ${product.manufacturer.name}  ${product.supportField1}   ${product.supportField2}</a>
					</h4>
					<p>
						${product.mainDescription} 
					</p>
				</div>
				<div class="ratings">
					<p class="pull-right">3 reviews</p>
					<p>
						<span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star"></span> <span class="glyphicon glyphicon-star-empty"></span> 4.0 stars
					</p>
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>