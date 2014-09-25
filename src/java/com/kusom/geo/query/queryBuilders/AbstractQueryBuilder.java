package com.kusom.geo.query.queryBuilders;

import com.kusom.geo.ESConnectionManager;
import com.kusom.geo.F7Request;
import com.kusom.geo.constants.QueryParameters;
import com.kusom.geo.query.api.QueryBuilder;
import com.kusom.geo.support.Parameters;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractQueryBuilder implements QueryBuilder {
    SearchRequestBuilder esQuery = null;
    String index;
    String record;

    /**
     * Initializes the parameters for SearchRequestBuilder
     * Calls the child implementation to add filters or query
     *
     * @param request
     * @param queryBuilder
     */
    @Override
    public void buildQuery(F7Request request, QueryBuilder queryBuilder) throws IOException {
        if (queryBuilder == null) {
            index = request.getIndex();
            record =request.getRecord();
            esQuery = ESConnectionManager.getClient(request.getClusterConfig()).prepareSearch()
                    .setIndices(index)
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setTypes(record);
            queryBuilder = this;
        } else esQuery = queryBuilder.getQuery();

        addFields(request, queryBuilder);
        //TODO implement sorting
       // addSort(request, queryBuilder);
        // TODO implement pagination
       // addPagination(request, queryBuilder);
        build(request, queryBuilder);
    }

    @Override
    public SearchRequestBuilder getQuery() {
        return esQuery;
    }

    protected void addFields(F7Request request, QueryBuilder queryBuilder) {
        String[] fields = Parameters.getFields(request);
        if (fields != null) {
            queryBuilder.getQuery().addFields(fields);
        }
    }

    /**
     * Method to be implemented by child class that adds either a filtered query or several other filters to the esQuery
     *
     * @param F7Request
     * @param query
     */
    public abstract void build(F7Request F7Request, QueryBuilder query) throws IOException;


}
