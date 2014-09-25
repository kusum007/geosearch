package com.kusom.geo

class SearchController {
    def queryService
    def requestService
       def responseService
    def index() {
        print(params)
        def requestMethod=request.method
        def isSearch = (requestMethod == "PUT" ||requestMethod=="POST") ? false : true
        def _request = requestService.parseSearchRequest(params, isSearch)
        def _response=queryService.serviceSearchDocument(_request)
        def _result = responseService.parseResponse(_response)
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "1800");//30 min
        render(contentType: 'text/json') {
                _result
        }
//        render result as JSON
    }
}