package com.gemicle.messagingsender.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "broadcast_list",
        "sender",
        "type",
        "text"
})
public class ViberBroadcastMessage {

    @JsonProperty("broadcast_list")
    private List<String> broadcastList;
    @JsonProperty("sender")
    private Sender sender;
    @JsonProperty("type")
    private String type;
    @JsonProperty("text")
    private String text;

    @JsonProperty("broadcast_list")
    public List<String> getBroadcastList() {
        return broadcastList;
    }

    @JsonProperty("broadcast_list")
    public void setBroadcastList(List<String> broadcastList) {
        this.broadcastList = broadcastList;
    }

    @JsonProperty("sender")
    public Sender getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }
}
