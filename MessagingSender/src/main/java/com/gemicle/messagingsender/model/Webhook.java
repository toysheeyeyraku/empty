package com.gemicle.messagingsender.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "url",
        "event_types"
})
public class Webhook {

    @JsonProperty("url")
    private String url;
    @JsonProperty("event_types")
    private List<String> eventTypes = null;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("event_types")
    public List<String> getEventTypes() {
        return eventTypes;
    }

    @JsonProperty("event_types")
    public void setEventTypes(List<String> eventTypes) {
        this.eventTypes = eventTypes;
    }

}