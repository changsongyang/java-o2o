package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.service.SynccontentService;

@Controller
public class SyncContentRedis {
	
	@Autowired
	SynccontentService synccontentService;
	
	@RequestMapping(value="/sync/content",method= RequestMethod.POST)
	@ResponseBody
	public TaotaoResult sysncContent(@RequestParam Long cid){
		try {
			TaotaoResult result = synccontentService.syncContent(cid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
}
