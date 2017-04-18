package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.EasyUITreeRusult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.po.TbContentCategory;
import com.taotao.po.TbContentCategoryExample;
import com.taotao.po.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
/**
 * 内容分类管理
 * 添加
 * 重命名
 * 删除
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

	@Override
	public TaotaoResult createContentCategory(Long parentId, String name) {
		
		TbContentCategory content = new TbContentCategory();
		content.setParentId(parentId);
		content.setName(name);
		//1(正常),2(删除)
		content.setStatus(1);
		//'排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数'
		content.setSortOrder(1);
		content.setIsParent(false);
		content.setCreated(new Date());
		content.setUpdated(new Date());
		
		tbContentCategoryMapper.insert(content);
		
		//数据插入后返回主键
		Long id = content.getId();
		
		TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()){
			parentNode.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		
		return TaotaoResult.ok(id);
	}

	@Override
	public TaotaoResult updateContentCategory(Long id, String name) {
		TbContentCategory conten = new TbContentCategory();
		conten.setId(id);
		conten.setName(name);
		conten.setUpdated(new Date());
		tbContentCategoryMapper.updateByPrimaryKeySelective(conten);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContentCategory(Long id) {
		deleteNode(id);
		return TaotaoResult.ok();
	}
	//递归删除节点
	private void deleteNode(Long id){
		//查询节点
		TbContentCategory result = tbContentCategoryMapper.selectByPrimaryKey(id);
		if(result.getIsParent()){
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andParentIdEqualTo(id);
			//查询子节点
			List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
			for (TbContentCategory tbContentCategory : list) {
				//判断是否有子节点有则递归
				if(tbContentCategory.getIsParent()){
					deleteNode(tbContentCategory.getId());
				}else{
					tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
				}
			}
			tbContentCategoryMapper.deleteByPrimaryKey(id);
		}else{
			tbContentCategoryMapper.deleteByPrimaryKey(id);
		}
	}
}
