package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.po.TbItemParam;
import com.taotao.po.TbItemParamExample;
import com.taotao.po.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
/**
 * 规格参数列表
 * 新增规格参数
 * 编辑规格参数
 * 删除规格参数
 * @author hulei
 *
 */
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

	@Override
	public TaotaoResult getItemParamById(Long cid) {
		
		TbItemParamExample example = new TbItemParamExample();
		
		Criteria createCriteria = example.createCriteria();
		
		createCriteria.andItemCatIdEqualTo(cid);
		
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		
		if(list != null && list.size() > 0){
			return TaotaoResult.ok(list);
		}
		
		return TaotaoResult.ok();
		
	}

	@Override
	public TaotaoResult insertItemParam(Long cid, String ParamData) {
		
		TbItemParam result = new TbItemParam();
		
		result.setItemCatId(cid);
		
		result.setParamData(ParamData);
		
		result.setCreated(new Date());
		
		result.setUpdated(new Date());
		
		itemParamMapper.insert(result);
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItemParam(Long ids) {
		itemParamMapper.deleteByPrimaryKey(ids);
		return TaotaoResult.ok();
	}

}
