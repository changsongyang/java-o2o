package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbItem;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.ItemService;

/**
 * 商品缓存信息 取商品缓存信息 更新商品缓存信息 删除商品缓存信息
 * 
 * @author hulei
 *
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	ItemService itemService;
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;

	@Override
	public TaotaoResult cartItem(Long itemId, Integer itemNum, HttpServletRequest request,
			HttpServletResponse response) {
		// 从cookie里取商品信息
		List<TbItem> list = getTbItem(request);
		Boolean flog = false;
		for (TbItem tbItem : list) {
			// 商品是否存在
			if (tbItem.getId().longValue() == itemId) {
				// 更新商品的数量
				tbItem.setNum(tbItem.getNum() + itemNum);
				flog = true;
				break;
			}
		}
		// 如果cookie中没有商品信息,查询商品信息存入cookie
		if (!flog) {
			TbItem result = itemService.getItemById(itemId);
			if (!StringUtils.isBlank(result.getImage())) {
				String imgs = result.getImage();
				String[] img = imgs.split(",");
				result.setImage(img[0]);
			}
			result.setNum(itemNum);
			list.add(result);
		}
		// 存入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), COOKIE_EXPIRE, true);
		return TaotaoResult.ok();
	}

	public List<TbItem> getTbItem(HttpServletRequest request) {
		try {
			// 从cookie里取商品信息
			String json = CookieUtils.getCookieValue(request, "TT_CART", true);
			if (StringUtils.isBlank(json)) {
				return new ArrayList<TbItem>();
			}
			// 将json转为java对象
			List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<TbItem>();
		}

	}

	@Override
	public TaotaoResult updateCart(HttpServletRequest request, HttpServletResponse response, Long itemid, Integer num) {
		// 从cookie里取商品信息
		List<TbItem> list = getTbItem(request);
		for (TbItem tbItem : list) {
			// 商品是否存在
			if (tbItem.getId().longValue() == itemid) {
				// 更新商品的数量
				tbItem.setNum(num);
				break;
			}
		}
		// 存入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), COOKIE_EXPIRE, true);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteCart(HttpServletRequest request, HttpServletResponse response, Long itemid) {
		try {
			// 从cookie里取商品信息
			List<TbItem> list = getTbItem(request);
			for (TbItem tbItem : list) {
				// 商品是否存在
				if (tbItem.getId().longValue() == itemid) {
					list.remove(tbItem);
					break;
				}
			}
			// 存入cookie
			CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), COOKIE_EXPIRE, true);
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}

}
