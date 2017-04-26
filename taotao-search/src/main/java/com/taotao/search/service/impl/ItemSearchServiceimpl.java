package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.SearchItem;
import com.taotao.commom.pojo.SearchResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.mapper.ItemSearchMapper;
import com.taotao.search.service.ItemSearchService;
/**
 * solr入库
 * solr出库
 * @author hulei
 *
 */
@Service
public class ItemSearchServiceimpl implements ItemSearchService {
	
	@Autowired
	ItemSearchMapper itemSearchMapper;
	
	@Autowired
	SolrServer solrServer;
	
	@Autowired
	SearchDao searchDao;
	
	@Override
	public TaotaoResult getItemList() throws  Exception {
		
		List<SearchItem> itemList = itemSearchMapper.getItemList();
		for (SearchItem item : itemList) {
			//创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			//添加域
			document.addField("id", item.getId());
			document.addField("item_title", item.getTitle());
			document.addField("item_sell_point", item.getSell_point());
			document.addField("item_price", item.getPrice());
			document.addField("item_image", item.getImage());
			document.addField("item_category_name", item.getItem_cat_name());
			document.addField("item_desc", item.getItem_desc());
			//写入索引库
			solrServer.add(document);
		}
		//提交
		solrServer.commit();
		return TaotaoResult.ok();
	}

	@Override
	public SearchResult searchResult(String queryString, int page, int rows) throws Exception {
		SolrQuery query = new SolrQuery();
		query.setQuery(queryString);
		//设置分页条件
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//设置默认搜索域
		query.set("df", "item_title");
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
		query.setHighlightSimplePost("</font>");
		//执行查询
		SearchResult searchResult = searchDao.searchResult(query);
		//计算总页数
		Long recordCount = searchResult.getRecordCount();
		int pageCount = (int) (recordCount / rows);
		if (recordCount % rows > 0) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		return searchResult;
	}

}
