package com.kusom.geo

import com.kusom.geo.query.api.QueryBuilder
import com.kusom.geo.query.queryBuilders.IndexQueryBuilder
import com.kusom.geo.query.queryBuilders.SearchQueryBuilder

class QueryService {
    def serviceSearchDocument(F7Request request) {
        def response = null
        if (request.isSearch) {
            def query = new SearchQueryBuilder()
            query.buildQuery(request, null)
            println "query " + query.getQuery().toString()

            response = query.getQuery().execute().actionGet()
        } else response = new IndexQueryBuilder().buildQuery(request)
        response
    }
}
