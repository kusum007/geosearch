
// environment specific settings
environments {
    development {
        elasticsearch {
            cluster_name="elasticsearch"
            host = "localhost"    //comma seperated hosts
            http_port = "9200"
            transport_port = "9300"
            index = "kusom"
            record="geoSearch"
        }
    }
    production {
        elasticsearch {
            cluster_name="elasticsearch"
            host = "localhost"    //comma seperated hosts
            http_port = "9200"
            transport_port = "9300"
            index = "kusom"
            record="geoSearch"
        }
    }
    test {
        elasticsearch {
            cluster_name="elasticsearch"
            host = "localhost"    //comma seperated hosts
            http_port = "9200"
            transport_port = "9300"
            index = "testkusom"
            record="geoSearch"
        }
    }

}