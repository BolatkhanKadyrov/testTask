package com.github.bolatkhankadyrov.helpers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyModel {
    private String name;
    private String catchPhrase;
    private String bs;

    CompanyModel(
            String name,
            String catchPhrase,
            String bs
    ) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    CompanyModel() {
    }
}
