package com.gemicle.messagingsender.clients;
/*
import com.gemicle.messagingsender.model.ViberBroadcastMessage;
import com.gemicle.messagingsender.model.ViberMessage;
import com.gemicle.messagingsender.model.Webhook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "https://chatapi.viber.com/pa", name = "VIBER-CLIENT")
public interface ViberClient {

    @GetMapping(value = "/get_account_info")
    String getAccountInfo(@RequestHeader("X-Viber-Auth-Token") String token);

    @PostMapping(value = "/set_webhook")
    String setWebhook(@RequestHeader("X-Viber-Auth-Token") String token, @RequestBody Webhook webhook);

    @PostMapping(value = "/send_message")
    void sendMessage(@RequestHeader("X-Viber-Auth-Token") String token, @RequestBody ViberMessage viberMessage);

    @PostMapping(value = "/broadcast_message")
    void sendBroadcastMessage(@RequestHeader("X-Viber-Auth-Token") String token, @RequestBody ViberBroadcastMessage viberBroadcastMessage);
}*/