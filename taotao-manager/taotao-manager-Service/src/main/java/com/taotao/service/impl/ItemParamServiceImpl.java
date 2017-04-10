package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.po.TbItemParam;
import com.taotao.po.TbItemParamExample;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	TbItemParamMapper itemParamMapper;

	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {

		// 设置分页
		PageHelper.startPage(page, rows);

		TbItemParamExample example = new TbItemParamExample();

		List<TbItemParam> list = itemParamMapper.selectByExample(example);

		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);

		EasyUIDataGridResult result = new EasyUIDataGridResult();

		result.setTotal(pageInfo.getTotal());

		result.setRows(list);

		return result;
	}

}
