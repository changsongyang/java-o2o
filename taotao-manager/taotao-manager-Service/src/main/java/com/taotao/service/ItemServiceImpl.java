package com.taotao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.po.TbItem;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	TbItemMapper tbItemMapper;
	@Override
	public TbItem getTbItemId(Long id) {
		TbItem selectByPrimaryKey = tbItemMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	@Override
	public List<TbItem> getTbItemList() {
		List<TbItem> selectByExample = tbItemMapper.selectByExample(null);
		return selectByExample;
	}

}
