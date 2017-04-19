package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.po.TbContent;
import com.taotao.po.TbContentExample;
import com.taotao.po.TbContentExample.Criteria;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	TbContentMapper tbContentMapper;
	
	@Override
	public TaotaoResult getContentList(Long categoryId) {
		
		try {
			TbContentExample example = new TbContentExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andCategoryIdEqualTo(categoryId);
			List<TbContent> list = tbContentMapper.selectByExample(example);
			return  TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}

}
