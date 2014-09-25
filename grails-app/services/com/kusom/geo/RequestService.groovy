package com.kusom.geo

import com.kusom.geo.constants.QueryParameters
import com.kusom.geo.support.ConfigurationFactory
import groovyx.net.http.*
import static groovyx.net.http.ContentType.JSON

class RequestService {

    def parseSearchRequest(params, isSearch) {

        F7Request request = new F7Request(requestParameters: new HashMap())
        def controller = params.remove("controller")
        def action = params.remove("action")
        def index = params.remove(QueryParameters.DEFAULT_INDEX)
        def type = params.remove(QueryParameters.RECORD_TYPE)
        def fields = [params.remove("fields") ?: []].flatten()
        //TODO only add the required fields, now just setting to default list of fields
        fields.add(QueryParameters.FIELD_PHONE)
        fields.add(QueryParameters.FIELD_CALLTIME)
        fields.add(QueryParameters.FIELD_LOCATION)
        fields.add(QueryParameters.FIELD_MESSAGE)
        fields.add(QueryParameters.FIELD_FORMATTED_ADDRESS)
        if (params.containsKey(QueryParameters.FIELD_LOCATION_LATITUDE) && params.containsKey(QueryParameters.FIELD_LOCATION_LONGITUDE)) {
            def latitute = Double.valueOf(params.remove(QueryParameters.FIELD_LOCATION_LATITUDE))
            def longitude = Double.valueOf(params.remove(QueryParameters.FIELD_LOCATION_LONGITUDE))
            def formatted_address=null;
            request.requestParameters.put(QueryParameters.FIELD_LOCATION, new Location(lat: latitute, lon: longitude))
            def http = new HTTPBuilder("http://maps.googleapis.com")
            String latlon=latitute+","+longitude
            http.request(Method.GET,JSON){
                uri.path="/maps/api/geocode/json"
                uri.query=[latlng:latlon,sensor:true]
                response.success = { resp, json ->
                    formatted_address=json.results.getAt(0).formatted_address
                }
                response.failure = { resp ->
                    formatted_address="result not found"
                }
            }
            request.requestParameters.put(QueryParameters.FIELD_FORMATTED_ADDRESS,formatted_address)
        }
        request.index = index ?: QueryParameters.DEFAULT_INDEX
        request.record = type ?: "geoSearch"
        request.requestParameters.putAll(params)
        request.isSearch=isSearch
        request.clusterConfig = ConfigurationFactory.clusterConfig;
        request.returnFields=fields
        request
    }

    public class Location {
        double lat;
        double lon;
    }
}
