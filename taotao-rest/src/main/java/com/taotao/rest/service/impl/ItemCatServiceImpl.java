package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.po.TbItemCat;
import com.taotao.po.TbItemCatExample;
import com.taotao.po.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.ItemCatNode;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类查询
 * 
 * @author hulei
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	TbItemCatMapper tbItemCatMapper;

	@Override
	public Map<String, List<Object>> getItemCatList() {
		Map<String, List<Object>> result = new HashMap<>();
		result.put("data",getList(0));
		return result;
	}

	/**
	 * 递归
	 * 
	 * @param prentId
	 * @return
	 */
	private List<Object> getList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<Object> resultList = new ArrayList<>();
		// 循环计数
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 如果为父节点
			if (tbItemCat.getIsParent()) {
				ItemCatNode node = new ItemCatNode();
				node.setUrl("/products/" + tbItemCat.getId() + ".html");
				// 判断是否为第一层节点
				if (parentId == 0) {
					node.setName("<a href='" + node.getUrl() + "'>" + tbItemCat.getName() + "</a>");
				} else {
					node.setName(tbItemCat.getName());
				}
				node.setItems(getList(tbItemCat.getId()));
				resultList.add(node);
			} else {
				String node = "/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
				resultList.add(node);
			}
			count++;
			// 第一个层循环，只取14条记录
			if (parentId == 0 && count >= 14) {
				break;
			}
		}
		return resultList;
	}

}
