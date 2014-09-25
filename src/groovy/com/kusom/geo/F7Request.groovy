package com.kusom.geo

import com.kusom.geo.config.ClusterConfig

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class F7Request {
    Map<String, Object> requestParameters;
    ClusterConfig clusterConfig
    String index
    String record
    def returnFields
    boolean isSearch=true

    public boolean hasParam(String key) {
        return requestParameters.containsKey(key);
    }

    public String param(String key) {
        return requestParameters.get(key).toString();
    }
    public Object get(String key){
        return requestParameters.get(key);
    }
}
