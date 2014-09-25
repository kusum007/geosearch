package com.kusom.geo.query.api;

import com.kusom.geo.F7Request;
import org.elasticsearch.action.search.SearchRequestBuilder;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
public interface QueryBuilder {
    SearchRequestBuilder getQuery();
    void buildQuery(F7Request request, QueryBuilder query) throws IOException;
}
