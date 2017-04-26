package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.commom.pojo.SearchResult;

public interface SearchDao {
	
	SearchResult searchResult (SolrQuery query) throws Exception;
}
