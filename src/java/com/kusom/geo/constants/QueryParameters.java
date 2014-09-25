package com.kusom.geo.constants;

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
public class QueryParameters {

    public static final String INDEX_NAME = "index";
    public static String DEFAULT_INDEX;  //this will be set in the ConfigurationManager class from datasource value.
    public static final String RECORD_TYPE = "type";
    public static final String SEARCH_FIELD = "search";
    public static final String FIELDS = "fields";
    public static final String ORDER_BY = "order";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE = "page";

    public static final String FIELD_PHONE="phoneNumber";
    public static final String FIELD_CALLTIME="callTime";
    public static final String FIELD_MESSAGE="message";
    public static final String FIELD_LOCATION="location";
    public static final String FIELD_LOCATION_LATITUDE="lat";
    public static final String FIELD_LOCATION_LONGITUDE="lon";
    public static final String FIELD_FORMATTED_ADDRESS="formatted_address";
    public static final float DEFAULT_DISTANCE=100.0f;
}
