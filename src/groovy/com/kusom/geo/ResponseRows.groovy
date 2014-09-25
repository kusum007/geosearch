package com.kusom.geo

/**
 * Created with IntelliJ IDEA.
 * User: kushum
 * Date: 7/25/13
 * To change this template use File | Settings | File Templates.
 */
class ResponseRows {
    private Iterator<List<ResponseFields>> response

    public ResponseRows(Iterator<List<ResponseFields>> _response) {
        response = _response
    }

    public Iterator<List<ResponseFields>> iterator() {
        return (response)
    }

}
