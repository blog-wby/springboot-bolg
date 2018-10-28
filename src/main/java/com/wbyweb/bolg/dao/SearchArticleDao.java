package com.wbyweb.bolg.dao;


import com.wbyweb.bolg.po.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface SearchArticleDao {

    SearchResult search(SolrQuery query) throws Exception;
}
