package com.taotao.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.dubbo.service.ItemService;
import com.taotao.mapper.TbItemMapper;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemExample;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	TbItemMapper tbItemMapper;

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 设置分页
		PageHelper.startPage(page, rows);

		TbItemExample example = new TbItemExample();

		List<TbItem> list = tbItemMapper.selectByExample(example);

		PageInfo<TbItem> pageInfo = new PageInfo<>(list);

		EasyUIDataGridResult result = new EasyUIDataGridResult();

		result.setTotal(pageInfo.getTotal());

		result.setRows(list);

		return result;
	}

}
