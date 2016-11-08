package com.holodniysvitanok.service;

/**
 * @author Gubin Vladislav
 * @version 1.0
 * 
 **/

public interface GlobalConfigProject {

	
	String ROOT_REDIRECT = "order"; // переадресация при корневом переходе
	String PREVIOUS_PAGE = "previousPage";
	/*
	 * Image setting
	 * */
	String IMAGE_BOX_URL = "imgbox";
	int height = 200;
	int width = 200;
	
	/*
	 * 
	 *Root setting 
	 * */
	String NAME_ROOT = "root";
	String LOGIN_ROOT = "root";
	String PASSWORD_ROOT = "root";
	long ROOT_ID = 1;
	int ROOT_ACCESS_LEVEL = 1;
	
	
	/*
	 * 
	 * Logger
	 * */
	boolean LOGGER_ON = false;
	
	
	
	/*
	 * 
	 * access level
	 * Чем ниже уровень, тем больше прав
	 * */
	int MIN_ACCESS_LEVEL = 10;
	int ACCESS_LEVEL_FOR_AUTHORIZATION = 5;
	int ACCESS_LEVEL_WORKER = 3;
	/*
	 * 
	 * 
	 * Currency name
	 * */
	String CURRENCY_NAME = "грн";
	
	
	
	
	/*    URL       */
	/*
	 * Authorization
	 * */
	String AUTHORIZATION = "authorization";
	
	/*
	 * 
	 * Category
	 * 
	 * */
	String ADD_CATEGORY = "addCategory";
	String DEL_CATEGORY = "delCategory";
	String SET_CATEGORY = "setCategory";
	String ACTION_DEL_CATEGORY = "actionDelCategory";
	String SHOW_CATEGORY = "showCategory";
	String FIND_CATEGORY = "findCategory";
	String CATEGORY = "category";
	String REVIEW_CATEGORY = "reviewCategory";
	
	
	/*
	 * 
	 * Product
	 * 
	 * */
	String ADD_PRODUCT = "addProduct";
	String ACTION_DEL_PRODUCT = "actionDelProduct";
	String DEL_PRODUCT = "delProduct";
	String SET_PRODUCT = "setProduct";
	String SHOW_PRODUCT = "showProduct";
	String FIND_PRODUCT = "findProduct";
	String PRODUCT = "product";
	String REVIEW_PRODUCT = "reviewProduct";
	String SELECT_CATEGORY = "selectCategory";
	String ACTUAL_EXCHANGE_RATE = "actualExchangeRate";
	
	/*
	 * 
	 * Manufacturer
	 * 
	 * */
	String ADD_MANUFACTURER = "addManufacturer";
	String SHOW_MANUFACTURER = "showManufacturer";
	String ACTION_DEL_MANUFACTURER = "actionDelManufacturer";
	String DEL_MANUFACTURER = "delManufacturer";
	String SET_MANUFACTURER = "setManufacturer";
	String FIND_MANUFACTURER = "findManufacturer";
	String MANUFACTURER = "manufacturer";
	String REVIEW_MANUFACTURER = "reviewManufacturer";
	
	
	/*
	 * 
	 * User
	 * */
	String USER = "user";
	String ADD_USER = "addUser";
	String FIND_USER = "findUser";
	String SHOW_USER = "showUser";
	String SET_USER = "setUser";
	String DEL_USER = "delUser";
	String EXIT_USER = "exitUser";
	String REVIEW_USER = "reviewUser";
	String INFO_USER = "infoUser";
	
	/*
	 * 
	 * Order
	 * */
	String ORDER = "order";
	String ACTIVE_ORDER = "activeOrder";
	String COMPLETE_ORDER = "completeOrder";
	String ADD_ORDER = "addOrder";
	String FIND_ORDER = "findOrder";
	String REVIEW_ORDER = "reviewOrder";
	String SET_ORDER = "setOrder";
	String CLOSE_ORDER = "closeOrder";
	String DEL_ORDER = "delOrder";
	String ADD_PRODUCT_IN_ORDER = "addProductInOrder";
	String SET_SELECT_ORDER = "setSelectOrder";
	String UPDATE_ORDER_DETAILS = "updateOrderDetails";
	String ACTION_DEL_ORDER = "actionDelOrder";
	String REVIEW_COMPLETE_ORDER = "reviewCompleteOrder";
	String EXPENSES_ORDER = "expensesOrder";
	String RETURN_ACTIVE_ORDER = "returnActiveOrder";

	/*
	 * 
	 * 
	 * Cashbox
	 * */
	String CASHBOX = "cashbox";
	String MAIN_CASHBOX = "mainCashbox";
	String STATUS_CASHBOX = "statusCashbox";
	String USER_CASHBOX = "userCashbox";
	String TAKE_CASHBOX = "takeCashbox";
	String LOG_CASHBOX = "logCashbox";
	
	/*
	 * 
	 * 
	 * Group
	 * */
	String GROUP = "group";
	String CONTROL_GROUP = "controlGroup";
	String UPDATE_GROUP = "updateGroup";
	
	
	
	
	/*    VIEW NAME       */
	/*
	 * 
	 * Authorization
	 * 
	 * */
	String VIEW_AUTHORIZATION = "authorization";
	/*
	 * 
	 * Category
	 * 
	 * */
	String VIEW_ADD_CATEGORY = "addCategory";
	String VIEW_DEL_CATEGORY = "delCategory";
	String VIEW_SET_CATEGORY = "setCategory";
	String VIEW_ACTION_DEL_CATEGORY = "actionDelCategory";
	String VIEW_SHOW_CATEGORY = "showCategory";
	String VIEW_FIND_CATEGORY = "findCategory";
	String VIEW_CATEGORY = "category";
	String VIEW_REVIEW_CATEGORY = "reviewCategory";
	/*
	 * 
	 * Product
	 * 
	 * */
	String VIEW_ADD_PRODUCT = "addProduct";
	String VIEW_ACTION_DEL_PRODUCT = "actionDelProduct";
	String VIEW_DEL_PRODUCT = "delProduct";
	String VIEW_SET_PRODUCT = "setProduct";
	String VIEW_SHOW_PRODUCT = "showProduct";
	String VIEW_FIND_PRODUCT = "findProduct";
	String VIEW_PRODUCT = "product";
	String VIEW_REVIEW_PRODUCT = "reviewProduct";
	String VIEW_SELECT_CATEGORY = "selectCategory";
	String VIEW_ACTUAL_EXCHANGE_RATE = "actualExchangeRate";
	/*
	 * 
	 * Manufacturer
	 * 
	 * */
	String VIEW_ADD_MANUFACTURER = "addManufacturer";
	String VIEW_SHOW_MANUFACTURER = "showManufacturer";
	String VIEW_ACTION_DEL_MANUFACTURER = "actionDelManufacturer";
	String VIEW_DEL_MANUFACTURER = "delManufacturer";
	String VIEW_SET_MANUFACTURER = "setManufacturer";
	String VIEW_FIND_MANUFACTURER = "findManufacturer";
	String VIEW_MANUFACTURER = "manufacturer";
	String VIEW_REVIEW_MANUFACTURER = "reviewManufacturer";
	/*
	 * 
	 * User
	 * */
	String VIEW_USER = "user";
	String VIEW_ADD_USER = "addUser";
	String VIEW_FIND_USER = "findUser";
	String VIEW_SHOW_USER = "showUser";
	String VIEW_SET_USER = "setUser";
	String VIEW_DEL_USER = "delUser";
	String VIEW_EXIT_USER = "exitUser";
	String VIEW_REVIEW_USER = "reviewUser";
	String VIEW_INFO_USER = "infoUser";
	/*
	 * 
	 * Order
	 * */
	String VIEW_ORDER = "order";
	String VIEW_ACTIVE_ORDER = "activeOrder";
	String VIEW_COMPLETE_ORDER = "completeOrder";
	String VIEW_ADD_ORDER = "addOrder";
	String VIEW_FIND_ORDER = "findOrder";
	String VIEW_REVIEW_ORDER = "reviewOrder";
	String VIEW_SET_ORDER = "setOrder";
	String VIEW_CLOSE_ORDER = "closeOrder";
	String VIEW_DEL_ORDER = "delOrder";
	String VIEW_ADD_PRODUCT_IN_ORDER = "addProductInOrder";
	String VIEW_SET_SELECT_ORDER = "setSelectOrder";
	String VIEW_UPDATE_ORDER_DETAILS = "updateOrderDetails";
	String VIEW_ACTION_DEL_ORDER = "actionDelOrder";
	String VIEW_REVIEW_COMPLETE_ORDER = "reviewCompleteOrder";
	String VIEW_EXPENSES_ORDER = "expensesOrder";
	String VIEW_RETURN_ACTIVE_ORDER = "returnActiveOrder";

	
	/*
	 * 
	 * 
	 * Cashbox
	 * */
	String VIEW_CASHBOX = "cashbox";
	String VIEW_STATUS_CASHBOX = "statusCashbox";
	String VIEW_USER_CASHBOX = "userCashbox";
	String VIEW_TAKE_CASHBOX = "takeCashbox";
	String VIEW_LOG_CASHBOX = "logCashbox";
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * Group
	 * */
	String VIEW_GROUP = "group";
	String VIEW_CONTROL_GROUP = "controlGroup";
	
	
	/*
	 * 
	 * 
	 * 
	 * ErrorMessage
	 * */
	String ERROR_PAGE = "errorPage";
	String NOT_SELECTED_ORDER = "Нет активного выбранного заказа. Сделай активным заказ, куда хочешь добавить товары";
	String NOT_FOUND_USER = "Пользователь не найден";
	String ERROR_AUTHORIZATION_MESSAGE = "Ошибка";
	String ERROR_TAKE_CASH = "Не вышло снять средства";
	String ERROR_PARAM = "Ошибка параметров";
}
