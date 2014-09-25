package com.kusom.geo

import org.elasticsearch.action.search.SearchResponse

class ResponseService {
    def parseResponse(response) {
        if (response instanceof SearchResponse ){
         ResponseRows responseRows = new ResponseRows(new ElasticSearchResponse(response));
         ResponseRenderer.renderResponse(responseRows);
        }
        else {

         ["status":"ok"]
        }
    }
}
