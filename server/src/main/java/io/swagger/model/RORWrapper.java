package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RORWrapper {

    @JsonProperty("@context")
    private String context;

    @JsonProperty("@type")
    private String type;

    private String name;

    private String url;

    private String description;

    private List<ROR> statistics;

    public RORWrapper(String context, String type, String name, String url, String description, List<ROR> statistics) {
        this.context = context;
        this.type = type;
        this.name = name;
        this.url = url;
        this.description = description;
        this.statistics = statistics;
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

    public List<ROR> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<ROR> statistics) {
        this.statistics = statistics;
    }
}
