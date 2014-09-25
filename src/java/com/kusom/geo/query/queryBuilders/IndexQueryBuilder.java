package com.kusom.geo.query.queryBuilders;

import com.kusom.geo.ESConnectionManager;
import com.kusom.geo.F7Request;
import com.kusom.geo.RequestService;
import com.kusom.geo.constants.QueryParameters;
import com.kusom.geo.query.api.QueryBuilder;
import com.kusom.geo.support.Parameters;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.joda.time.format.DateTimeFormatter;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
public class IndexQueryBuilder {
    public IndexResponse buildQuery(F7Request request) throws IOException {
        String index = request.getIndex();
        String record = request.getRecord();
        XContentBuilder source = XContentFactory.jsonBuilder();
        source.startObject();
        add(source, request, QueryParameters.FIELD_PHONE, QueryParameters.FIELD_CALLTIME, QueryParameters.FIELD_MESSAGE,QueryParameters.FIELD_FORMATTED_ADDRESS);
        if (request.hasParam(QueryParameters.FIELD_LOCATION)) {
            RequestService.Location location = (RequestService.Location) request.get(QueryParameters.FIELD_LOCATION);
            double latitude=location.getLat();
            double longitude=location.getLon();
            source.startObject(QueryParameters.FIELD_LOCATION).field(QueryParameters.FIELD_LOCATION_LATITUDE, latitude).field(QueryParameters.FIELD_LOCATION_LONGITUDE, longitude).endObject();
        }
        source.endObject();
        String callTime=request.hasParam(QueryParameters.FIELD_CALLTIME)?request.param(QueryParameters.FIELD_CALLTIME):new DateTime().toString();
        Client client=ESConnectionManager.getClient(request.getClusterConfig());
        IndexResponse response= client.prepareIndex(index, record, request.param(QueryParameters.FIELD_PHONE)+":"+callTime)
                .setSource(source).execute().actionGet();
        client.admin().indices().prepareRefresh().execute().actionGet();
        return response;
    }

    public void add(XContentBuilder builder, F7Request request, String... fields) throws IOException {
        for (String field : fields)
            if (request.hasParam(field))
                builder.field(field, request.param(field));
    }
}
