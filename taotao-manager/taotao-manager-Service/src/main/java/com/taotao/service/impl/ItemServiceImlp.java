package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemExample;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImlp implements ItemService {
	
	@Autowired
	TbItemMapper tbItemMapper;
	
	@Autowired
	TbItemDescMapper tbItemDescMapper; 

	@Override
	public EasyUIDataGridResult getItemLise(int page, int rows) {
		
		//设置分页
		PageHelper.startPage(page, rows);
		
		TbItemExample example  = new TbItemExample();
		
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		
		result.setTotal(pageInfo.getTotal());
		
		result.setRows(list);
		
		return result;
	}

	@Override
	public TaotaoResult createItem(TbItem item, String desc) {
		
		Long itemId = IDUtils.genItemId();
				
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除'
		item.setStatus((byte)1);
		
		item.setCreated(new Date());
		
		item.setUpdated(new Date());
		
		//添加商品
		tbItemMapper.insert(item);
		
		TbItemDesc itemDesc  = new TbItemDesc();
		
		itemDesc.setItemId(itemId);
		
		itemDesc.setItemDesc(desc);
		
		itemDesc.setCreated(new Date());
		
		itemDesc.setUpdated(new Date());
		
		//添加商品描述
		tbItemDescMapper.insert(itemDesc);
		
		return TaotaoResult.ok();
	}

}
