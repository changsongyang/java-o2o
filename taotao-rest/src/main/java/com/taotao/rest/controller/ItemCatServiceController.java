package com.taotao.rest.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatServiceController {
	
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public Object list(String callback){
		Map<String, List<Object>> result = itemCatService.getItemCatList();
		if(StringUtils.isBlank(callback)){
			return result;
		}
		//解决中文乱码4.1支持
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
