package com.github.bolatkhankadyrov.helpers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoModel {
    private float lat;
    private float lng;

    public GeoModel(
            float lat,
            float lng
    ) {
        this.lat = lat;
        this.lng = lng;
    }

    GeoModel() {
    }

    @Override
    public String toString() {
        return "GeoModel{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
