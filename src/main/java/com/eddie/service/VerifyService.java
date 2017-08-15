package com.eddie.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Created by EddieChoCho on 2017/8/14.
 */
@Service
public class VerifyService {

    public boolean verifyingUsersResponse(String response, String url, String secret){
        Client client = ClientBuilder.newClient();
        WebTarget API = client.target(url);
        Form requestData = new Form();
        requestData.param("secret", secret);
        requestData.param("response", response);
        JsonNode node = API.request(MediaType.APPLICATION_FORM_URLENCODED)
                .buildPost(Entity.entity(requestData, MediaType.APPLICATION_FORM_URLENCODED))
                .invoke(JsonNode.class);
        return node.get("success").asBoolean();
    }
}
