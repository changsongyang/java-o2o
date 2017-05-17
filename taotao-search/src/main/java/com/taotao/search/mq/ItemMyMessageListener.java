package com.taotao.search.mq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.commom.pojo.SearchItem;
import com.taotao.search.mapper.ItemSearchMapper;

public class ItemMyMessageListener implements MessageListener{
	
	@Autowired
	private ItemSearchMapper itemSearchMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public void onMessage(Message message) {
		try {
			//从消息中取商品id
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemid = Long.parseLong(text);
			System.out.println("接收商品id" + itemid);
			//等待事务提交
			Thread.sleep(1000);
			//查询商品信息
			SearchItem searchItem = itemSearchMapper.getItem(itemid);
			//创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			//向文档对象中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getItem_cat_name());
			document.addField("item_desc", searchItem.getItem_desc());
			//把文档对象写入索引库
			solrServer.add(document);
			//提交
			solrServer.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
