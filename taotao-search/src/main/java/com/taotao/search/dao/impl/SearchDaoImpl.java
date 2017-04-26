package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.commom.pojo.SearchItem;
import com.taotao.commom.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;

@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	SolrServer solrServer;

	@Override
	public SearchResult searchResult(SolrQuery query) throws Exception {
		// 查询
		QueryResponse response = solrServer.query(query);
		SolrDocumentList results = response.getResults();
		List<SearchItem> itemList = new ArrayList<>();
		for (SolrDocument solrDocument : results) {
			//创建一个SearchItem对象
			SearchItem item = new SearchItem();
			item.setItem_cat_name((String)solrDocument.get("item_category_name"));
			item.setId((String) solrDocument.get("id"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setPrice((Long) solrDocument.get("item_price"));
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String itemTitle = "";
			if (list != null && list.size() > 0) {
				//取高亮后的结果
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) solrDocument.get("item_title");
			}
			item.setTitle(itemTitle);
			//添加到列表
			itemList.add(item);
		}
		SearchResult setResult = new SearchResult();
		setResult.setItemList(itemList);
		//查询结果总数量
		setResult.setRecordCount(results.getNumFound());
		
		return setResult;
	}

}
