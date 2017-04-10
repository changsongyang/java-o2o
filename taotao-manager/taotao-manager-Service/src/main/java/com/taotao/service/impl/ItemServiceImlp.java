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
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemExample;
import com.taotao.po.TbItemParamItem;
import com.taotao.service.ItemService;
/**
 * 商品列表
 * 新增商品列表
 * 编辑商品列表
 * 删除商品列表
 * 商品上架
 * 商品下架
 * @author hulei
 *
 */
@Service
public class ItemServiceImlp implements ItemService {
	
	@Autowired
	TbItemMapper tbItemMapper;
	
	@Autowired
	TbItemDescMapper tbItemDescMapper; 
	
	@Autowired
	TbItemParamItemMapper tbItemParamItemMapper;

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		
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
	public TaotaoResult createItem(TbItem item, String desc,String itemParams) {
		
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
		
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		
		tbItemParamItem.setItemId(itemId);
		
		tbItemParamItem.setParamData(itemParams);
		
		tbItemParamItem.setCreated(new Date());
	
		tbItemParamItem.setUpdated(new Date());
		
		//添加商品规格
		tbItemParamItemMapper.insert(tbItemParamItem);
		
		return TaotaoResult.ok();
	}

}
