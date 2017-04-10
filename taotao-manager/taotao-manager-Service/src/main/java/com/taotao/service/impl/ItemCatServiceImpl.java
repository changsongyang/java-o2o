package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.EasyUITreeRusult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.po.TbItemCat;
import com.taotao.po.TbItemCatExample;
import com.taotao.po.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUITreeRusult> getItemCat(long parentId) {

		TbItemCatExample example = new TbItemCatExample();

		Criteria createCriteria = example.createCriteria();

		createCriteria.andParentIdEqualTo(parentId);

		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);

		List<EasyUITreeRusult> rusult = new ArrayList<>();

		for (TbItemCat tbItemCat : list) {
			EasyUITreeRusult node = new EasyUITreeRusult(tbItemCat.getId(), tbItemCat.getName(), 
					tbItemCat.getIsParent()?"closed":"open");
			rusult.add(node);
		}
		return rusult;
	}

}
