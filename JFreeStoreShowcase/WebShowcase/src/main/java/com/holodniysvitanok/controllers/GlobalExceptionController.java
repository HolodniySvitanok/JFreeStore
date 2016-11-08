package com.holodniysvitanok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;

/**
 * Created by Admin on 08.11.2016.
 */

//@ControllerAdvice
// public class GlobalExceptionController {
//
//    @Autowired
//    private ServletContext context;
//
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(Exception exception) {
//        ModelAndView model = new ModelAndView();
//        model.setViewName("redirect: "+ context.getContextPath());
//        return model;
//    }
//}
