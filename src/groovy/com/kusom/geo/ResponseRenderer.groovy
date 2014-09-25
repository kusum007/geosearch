package com.kusom.geo

import grails.converters.JSON
import groovyx.net.http.Method
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.Test

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class ResponseRenderer {
    public static String RESULTSET = "result_sets";

    public static JSONObject renderResponse(ResponseRows responseRows) {

        def resultSetJSON = resultSetJson(responseRows);
        JSONObject returnJSON = new JSONObject();
        returnJSON.put(RESULTSET,resultSetJSON);
        return returnJSON;
    }

    public static Object resultSetJson(ResponseRows responseRows) {
        def returnJSON = new LinkedHashMap<String,Object>();
        int responseCounter = 0;
        for (List<ResponseFields> responseFields: responseRows) {
            JSONObject innerJSON = new JSONObject();
            for (ResponseFields responseField: responseFields) {
                if (responseField.name == null) continue;
                innerJSON.accumulate(responseField.name, responseField.value);
            }
            returnJSON.put(Integer.toString(responseCounter), innerJSON);
            responseCounter++;
        }
        return returnJSON
    }

}
