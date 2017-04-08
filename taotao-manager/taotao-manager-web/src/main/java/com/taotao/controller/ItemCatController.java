package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUITreeRusult;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public List<EasyUITreeRusult> getItemCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EasyUITreeRusult> itemCat = itemCatService.getItemCat(parentId);
		return itemCat;
	}
}
