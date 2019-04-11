package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ContingencyTableWrapper implements Serializable {

    @JsonProperty("@context")
    private String context;

    @JsonProperty("@type")
    private String type;

    private String name;

    private String url;

    private String description;

    private List<ContingencyTable> outcome;

    public ContingencyTableWrapper(String context, String type, String name, String url, String description, List<ContingencyTable> outcome) {
        this.context = context;
        this.type = type;
        this.name = name;
        this.url = url;
        this.description = description;
        this.outcome = outcome;
    }

    @JsonProperty("@context")
    public String getContext() {

        return context;
    }

    @JsonProperty("@context")
    public void setContext(String context) {
        this.context = context;
    }

    @JsonProperty("@type")
    public String getType() {
        return type;
    }

    @JsonProperty("@type")
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ContingencyTable> getOutcome() {
        return outcome;
    }

    public void setOutcome(List<ContingencyTable> outcome) {
        this.outcome = outcome;
    }

}
