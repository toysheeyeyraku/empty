package com.gemicle.messagingsender.model;

import lombok.Data;

import java.util.List;

@Data
public class MessageObject {
    String message;
    List<String> recipients;
}