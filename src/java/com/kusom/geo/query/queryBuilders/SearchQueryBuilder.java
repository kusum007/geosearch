package com.kusom.geo.query.queryBuilders;

import com.kusom.geo.F7Request;
import com.kusom.geo.RequestService;
import com.kusom.geo.constants.QueryParameters;
import com.kusom.geo.query.api.QueryBuilder;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.fielddata.fieldcomparator.SortMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortBuilders;

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
public class SearchQueryBuilder extends AbstractQueryBuilder{

    @Override
    public void build(F7Request request, QueryBuilder query) {
          double distance=request.hasParam("distance")?Double.valueOf(request.param("distance")):QueryParameters.DEFAULT_DISTANCE;
        FilterBuilder filter=null;
        BaseQueryBuilder baseQueryBuilder=request.hasParam(QueryParameters.FIELD_PHONE)?QueryBuilders.termQuery(QueryParameters.FIELD_PHONE,request.param(QueryParameters.FIELD_PHONE)):
                QueryBuilders.matchAllQuery();
        if(request.hasParam(QueryParameters.FIELD_LOCATION)){
            RequestService.Location location;
            location = (RequestService.Location) request.get(QueryParameters.FIELD_LOCATION);
            filter=FilterBuilders.geoDistanceFilter("location").distance(distance, DistanceUnit.KILOMETERS).lat(location.getLat())
                     .lon(location.getLon()).filterName(QueryParameters.FIELD_LOCATION);
            query.getQuery().addSort(SortBuilders.geoDistanceSort(QueryParameters.FIELD_LOCATION).unit(DistanceUnit.KILOMETERS).point(location.getLat(),location.getLon()));
        }

        if(filter==null)filter=FilterBuilders.matchAllFilter();
        query.getQuery().setQuery(QueryBuilders.filteredQuery(baseQueryBuilder,filter));

    }
}
