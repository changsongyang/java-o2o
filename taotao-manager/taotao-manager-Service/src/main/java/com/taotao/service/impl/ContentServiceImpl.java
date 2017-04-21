package com.taotao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.po.TbContent;
import com.taotao.po.TbContentExample;
import com.taotao.po.TbContentExample.Criteria;
import com.taotao.service.ContentService;
/**
 * 内容管理
 * 新增
 * 编辑
 * 删除
 * @author hulei
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	TbContentMapper tbContentMapper;
	
	@Override
	public TaotaoResult sevaContent(TbContent tbContent) {
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentMapper.insert(tbContent);
		Map<String, String> map = new HashMap<String, String>();
		map.put("cid", tbContent.getCategoryId()+"");
		HttpClientUtil.doPost("http://localhost:9998/rest/sync/content",map);
		return TaotaoResult.ok();
	}

	@Override
	public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
		// 设置分页
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		if(categoryId != 0){
			Criteria createCriteria = example.createCriteria();
			createCriteria.andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	@Override
	public TaotaoResult updateContent(TbContent tbContent) {
		tbContent.setUpdated(new Date());
		tbContentMapper.updateByPrimaryKey(tbContent);
		Map<String, String> map = new HashMap<String, String>();
		map.put("cid", tbContent.getCategoryId()+"");
		HttpClientUtil.doPost("http://localhost:9998/rest/sync/content",map);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContent(Long ids) {
		TbContent selectByPrimaryKey = tbContentMapper.selectByPrimaryKey(ids);
		tbContentMapper.deleteByPrimaryKey(ids);
		Map<String, String> map = new HashMap<String, String>();
		map.put("cid", selectByPrimaryKey.getCategoryId() + "");
		HttpClientUtil.doPost("http://localhost:9998/rest/sync/content",map);
		return TaotaoResult.ok();
	}

}
