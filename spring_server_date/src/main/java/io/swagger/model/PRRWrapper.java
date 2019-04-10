package io.swagger.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PRRWrapper implements Serializable {

    @JsonProperty("@context")
    private String context;

    @JsonProperty("@type")
    private String type;

    private String name;

    private String url;

    private String description;

    private List<PRR> statistics;

    public PRRWrapper(String context, String type, String name, String url, String description, List<PRR> statistics) {
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

    public List<PRR> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<PRR> statistics) {
        this.statistics = statistics;
    }
}
