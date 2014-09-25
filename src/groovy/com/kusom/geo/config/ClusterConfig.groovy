package com.kusom.geo.config


/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class ClusterConfig {
    public static String ES_DEFAULT_CLUSTER_NAME="elasticsearch";
    Map<String,ServerConfig> nodes;
    String clusterName;
}
