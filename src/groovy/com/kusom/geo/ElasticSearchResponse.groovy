package com.kusom.geo

import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.search.SearchHit
import org.elasticsearch.search.SearchHitField

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class ElasticSearchResponse implements Iterator<List<ResponseFields>>{
    Iterator<SearchHit> responseIterator;

    public ElasticSearchResponse(SearchResponse responseList) {
        responseIterator = responseList.getHits().iterator();
    }

    private List<ResponseFields> getResponseFields(SearchHit hit) {
        int i = 0
        Map<String, ResponseFields> ResponseFields = new HashMap<String, ResponseFields>();
        if (hit.getFields() == null) return (List<ResponseFields>) ResponseFields.values();
        for (Map.Entry<String, SearchHitField> hitEntry: hit.getFields().entrySet()) {
            def value = hitEntry.getValue().value
            ResponseFields field = getResponseFields(hitEntry.key, value)
            if (field.value == null) continue;
            field.name = hitEntry.getKey();
            ResponseFields.put(field.name, field);
        }
        return new ArrayList<ResponseFields>(ResponseFields.values());
    }


    public boolean hasNext() {
        return responseIterator.hasNext();
    }

    public List<ResponseFields> next() {
        if (hasNext()) {
            SearchHit hit = responseIterator.next();
            return (getResponseFields(hit));
        }
        return null;
    }

    def getResponseFields(String name, Object value) {
        def ResponseFields = new ResponseFields()
        ResponseFields.name = name
        ResponseFields.type = "String"
        ResponseFields.value = value
        ResponseFields
    }

    public void remove() {
        responseIterator.remove();
    }
}
