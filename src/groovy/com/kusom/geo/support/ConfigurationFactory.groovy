package com.kusom.geo.support

import com.kusom.geo.config.ClusterConfig
import com.kusom.geo.config.ConfigurationManager

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class ConfigurationFactory {

    public static ClusterConfig clusterConfig = null;

    public static ClusterConfig getClusterConfig() {
        if (clusterConfig == null) {
            init()
        }
        return clusterConfig
    }

    @SuppressWarnings("unchecked")
    public static void init() {
        ConfigurationManager configurationManager = new ConfigurationManager();
        clusterConfig = configurationManager.getClusterConfig();
    }
}
