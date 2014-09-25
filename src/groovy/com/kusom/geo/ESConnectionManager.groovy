package com.kusom.geo

import com.kusom.geo.config.ClusterConfig
import com.kusom.geo.config.ServerConfig
import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.ImmutableSettings
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class ESConnectionManager {
    static Client client = null;

    public static Client getClient(ClusterConfig clusterConfig) {
        if (client == null) client = getInstance(clusterConfig);
        return client;
    }

    private static Client getInstance(ClusterConfig clusterConfig) {
        if (clusterConfig.getNodes().size() == 0) {
            throw new RuntimeException("No servers specified. Refer to ServerConfig.");
        }
        String esClusterName = !clusterConfig.getClusterName().isEmpty() ? clusterConfig.getClusterName() : ClusterConfig.ES_DEFAULT_CLUSTER_NAME;
        System.out.println("Connection attempt to cluster: " + esClusterName);
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", esClusterName).build();
        TransportClient client = new TransportClient(settings);
        for (ServerConfig serverConfig : clusterConfig.getNodes().values()) {
            System.out.println("Adding cluster node:" + serverConfig.getName() + "   HostName:: " + serverConfig.getHostname() + "   Port:: " + serverConfig.getPort());
            client.addTransportAddress(new InetSocketTransportAddress(serverConfig.getHostname(), serverConfig.getPort()));
        }
        return client;
    }
}
