package com.taotao.search.mapper;

import java.util.List;

import com.taotao.commom.pojo.SearchItem;

public interface ItemSearchMapper {
	
	List<SearchItem> getItemList();
	SearchItem getItem(Long itemid);
}
