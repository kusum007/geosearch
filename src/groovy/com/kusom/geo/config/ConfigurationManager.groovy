package com.kusom.geo.config

import com.kusom.geo.constants.QueryParameters
import grails.util.Holders


/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class ConfigurationManager {
    public ClusterConfig getClusterConfig() {
        def es_host = Holders.config.elasticsearch.host
        def es_port = Holders.config.elasticsearch.transport_port
        def cluster_name = Holders.config.elasticsearch.cluster_name
        QueryParameters.DEFAULT_INDEX = Holders.config.elasticsearch.index

        ClusterConfig clusterConfig = new ClusterConfig()

        def nodesMap = [:]
        es_host.toString().split(",").eachWithIndex { it, idx ->
            ServerConfig server = new ServerConfig()
            def nodeCount = idx + 1
            server.name = "Node" + nodeCount
            server.hostname = it.toString()
            server.port = Integer.parseInt(es_port)
            nodesMap.put(server.name, server)
        }

        clusterConfig.nodes = nodesMap
        clusterConfig.clusterName = cluster_name
        clusterConfig
    }
}
