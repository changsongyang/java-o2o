package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemParamItem;
import com.taotao.portal.service.ItemService;

/**
 * 调用webservice
 * 
 * @author hulei
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REDIS_ITEM_LIST_URL}")
	private String REDIS_ITEM_LIST_URL;
	@Value("${REDIS_ITEM_DES_URL}")
	private String REDIS_ITEM_DES_URL;
	@Value("${REDIS_ITEM_SPRC_URL}")
	private String REDIS_ITEM_SPRC_URL;

	@Override
	public TbItem getItemById(Long itemId) {
		Map<String, String> vo = new HashMap<>();
		vo.put("itemId", itemId + "");
		String json = HttpClientUtil.doPost(REST_BASE_URL + REDIS_ITEM_LIST_URL, vo);
		// 将json 转为java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
		TbItem result = (TbItem) taotaoResult.getData();
		return result;
	}

	@Override
	public String getItemDesc(Long itemId) {
		Map<String, String> vo = new HashMap<>();
		vo.put("itemId", itemId + "");
		String json = HttpClientUtil.doPost(REST_BASE_URL + REDIS_ITEM_DES_URL, vo);
		// 将json 转为java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
		TbItemDesc result = (TbItemDesc) taotaoResult.getData();
		return result.getItemDesc();
	}

	@Override
	public String getItemParamById(Long itemId) {
		Map<String, String> vo = new HashMap<>();
		vo.put("itemId", itemId + "");
		String json = HttpClientUtil.doPost(REST_BASE_URL + REDIS_ITEM_SPRC_URL, vo);
		// 将json 转为java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
		TbItemParamItem result = (TbItemParamItem) taotaoResult.getData();
		String paramJson = result.getParamData();
		// 把规格参数的json数据转换成java对象
		// 转换成java对象
		List<Map> mapList = JsonUtils.jsonToList(paramJson, Map.class);
		// 遍历list生成html
		StringBuffer sb = new StringBuffer();

		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map map : mapList) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
			sb.append("		</tr>\n");
			// 取规格项
			List<Map> mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
				sb.append("			<td>" + map2.get("v") + "</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");

		return sb.toString();

	}

}
