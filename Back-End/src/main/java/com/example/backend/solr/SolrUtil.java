package com.example.backend.solr;

import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SolrUtil {

    private final SolrClient client;

    private boolean isIndexed = false;

    public SolrUtil() {
        String solrUrl = "http://localhost:8983/solr";
        client = new HttpSolrClient.Builder(solrUrl).build();
    }

    public List<Book> fullTextSearch(String filterText){
        SolrQuery query = new SolrQuery("description:" + filterText);
        query.setSort("description", SolrQuery.ORDER.desc);
        QueryResponse responseOne;
        List<Book> result = new ArrayList<>();
        try {
            responseOne = client.query("book", query);
            result = responseOne.getBeans(Book.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
