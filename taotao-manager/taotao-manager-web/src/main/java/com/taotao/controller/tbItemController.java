package com.taotao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.po.TbItem;
import com.taotao.service.ItemService;

@Controller
public class tbItemController {

	@Autowired
	ItemService itemService;

	@RequestMapping("/detail/{id}")
	@ResponseBody
	public Map<String, Object> detail(@PathVariable Long id) {

		Map<String, Object> result = new HashMap<>();

		TbItem tbItemId = itemService.getTbItemId(id);

		if (tbItemId != null) {
			result.put("succees", true);
			result.put("errorMessage", "查询成功");
			result.put("data", tbItemId);
			return result;
		} else {
			result.put("succees", false);
			result.put("errorMessage", "参数传递有误");
			result.put("data", tbItemId);
			return result;
		}

	}
	
	@RequestMapping(value="/list.json",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list() {

		Map<String, Object> result = new HashMap<>();

		List<TbItem> tbItemList = itemService.getTbItemList();

		if (tbItemList != null) {
			result.put("succees", true);
			result.put("errorMessage", "查询成功");
			result.put("data", tbItemList);
			return result;
		} else {
			result.put("succees", false);
			result.put("errorMessage", "参数传递有误");
			result.put("data", null);
			return result;
		}

	}
}
