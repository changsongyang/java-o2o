package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.commom.pojo.KindEditorResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.KindEditorService;

@Controller
public class KindEditoController {
	@Autowired
	KindEditorService kindEditorService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile){
		System.out.println(uploadFile);
		KindEditorResult resule = kindEditorService.uploadPic(uploadFile);
		return JsonUtils.objectToJson(resule);
	}
}
