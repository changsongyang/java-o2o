package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.EasyUITreeRusult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.po.TbContentCategory;
import com.taotao.po.TbContentCategoryExample;
import com.taotao.po.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
/**
 * 内容分类管理
 * @author hulei
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EasyUITreeRusult> getContentCategoryList(Long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example );
		 List<EasyUITreeRusult> result = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeRusult node = new EasyUITreeRusult(tbContentCategory.getId(), tbContentCategory.getName(), 
					tbContentCategory.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}

}
