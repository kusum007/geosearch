package com.kusom.geo.support;

import com.kusom.geo.F7Request;
import com.kusom.geo.constants.QueryParameters;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
public class Parameters {
    public static String getIndex(F7Request request) {
        return request.hasParam(QueryParameters.INDEX_NAME) ? request.param(QueryParameters.INDEX_NAME) : QueryParameters.DEFAULT_INDEX;
    }

    public static String getType(F7Request request) {
        return request.param(QueryParameters.RECORD_TYPE);
    }

    public static String[] getFields(F7Request request) {
        Object fieldsParam=request.getReturnFields();
        if(fieldsParam==null)return null;
        if(fieldsParam instanceof Iterable)return ((List<String>)fieldsParam).toArray(new String[((List<String>)fieldsParam).size()]);
        return getArray(request.param(QueryParameters.FIELDS));
    }
    public static void removeFields(F7Request request){
        request.getRequestParameters().remove(QueryParameters.FIELDS);
    }

    public static String[] getArray(String item) {
        return item == null?new String[]{}: item.replace("[", "").replace("]", "").replaceAll(" ","").trim().split(",");
    }
    public static Object removeParam(F7Request request,String param){
        return request.getRequestParameters().remove(param);
    }
}
