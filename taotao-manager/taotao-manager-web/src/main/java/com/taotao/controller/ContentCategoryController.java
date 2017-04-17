package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUITreeRusult;
import com.taotao.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	ContentCategoryService categoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeRusult> list(@RequestParam(value="id", defaultValue="0")Long parentId){
		List<EasyUITreeRusult> result = categoryService.getContentCategoryList(parentId);
		return result;
	}

}
