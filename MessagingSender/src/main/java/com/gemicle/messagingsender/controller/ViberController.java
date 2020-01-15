package com.gemicle.messagingsender.controller;
/*
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemicle.messagingsender.document.User;
import com.gemicle.messagingsender.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

@Slf4j
//@RestController
public class ViberController {

    private final UserService userService;

    public ViberController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public void webhook(@RequestBody String json) throws IOException {
        log.info(json);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(json);
        String event = node.get("event").asText();
        switch (event) {
            case "conversation_started":
                String userId = node.get("context").textValue();
                String viberUserId = node.path("user").get("id").textValue();
                log.info(userId);
                log.info(viberUserId);
                User user = userService.findUserByUserId(userId);
                user.setViberUserId(viberUserId);
                userService.save(user);
                break;
            default:
                log.info("Unknown event");
                break;
        }
    }

    @GetMapping("/viber")
    public void viber(HttpServletResponse httpServletResponse) {
        log.info("Viber redirect");
        httpServletResponse.setHeader("Location", "viber://pa?chatURI=razakornotificationtestbot&context=1&text=Start");
        httpServletResponse.setStatus(302);
    }
}*/