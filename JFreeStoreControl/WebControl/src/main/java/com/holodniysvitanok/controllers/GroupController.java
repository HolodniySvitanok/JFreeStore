package com.holodniysvitanok.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.holodniysvitanok.entity.ConfigEntity;
import com.holodniysvitanok.entity.Group;
import com.holodniysvitanok.service.GlobalConfigProject;
import com.holodniysvitanok.service.GroupService;

@Controller
@RequestMapping(value = GlobalConfigProject.GROUP)
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView group(ModelAndView model){

		model.setViewName("redirect: "+context.getContextPath()+"/"+GlobalConfigProject.GROUP+"/"+GlobalConfigProject.CONTROL_GROUP);
		
		return model;
	}
	
	
	
	@RequestMapping(value= GlobalConfigProject.CONTROL_GROUP, method = RequestMethod.GET)
	public ModelAndView controlGroup(ModelAndView model){
		
		List<Group> groupList = groupService.getGroupList();
		
		model.addObject("groupList", groupList);
		model.setViewName(GlobalConfigProject.VIEW_CONTROL_GROUP);
		
		return model;
	}
	
	
	
	@RequestMapping(value= GlobalConfigProject.CONTROL_GROUP, method = RequestMethod.POST)
	public ModelAndView actionControlGroup(ModelAndView model, WebRequest request ){
		
		model.setViewName("redirect: "+context.getContextPath()+"/"+GlobalConfigProject.GROUP+"/"+GlobalConfigProject.CONTROL_GROUP);
		
		String name = request.getParameter("name");
		
		if(name.length() >= ConfigEntity.NAME_LENGTH){
			return model;
		}
		
		Group group = new Group();
		group.setName(name);
		groupService.saveOrUpdate(group);
		
		return model;
	}
	
	@RequestMapping(value= GlobalConfigProject.UPDATE_GROUP, method = RequestMethod.POST)
	public ModelAndView updateControlGroup(ModelAndView model, WebRequest request ){
		
		model.setViewName("redirect: "+context.getContextPath()+"/"+GlobalConfigProject.GROUP+"/"+GlobalConfigProject.CONTROL_GROUP);
		
		String[] id = request.getParameterValues("id");
		String[] name = request.getParameterValues("name");
		String[] delete = request.getParameterValues("delete");
		
		
		for(int i=0; i < id.length; i++){
			Group group = new Group(Long.parseLong(id[i]), name[i]);
			groupService.saveOrUpdate(group);
		}
		
		if(delete != null && delete.length != 0){
			
			for(String del : delete){
				groupService.delete(new Group(Long.parseLong(del)));
			}
			
		}
		
		return model;
	}

	
	
	
}
