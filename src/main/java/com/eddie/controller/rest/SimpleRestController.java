package com.eddie.controller.rest;

import com.eddie.service.VerifyService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Created by EddieChoCho on 2017/8/14.
 */
@RestController
@RequestMapping("/rest")
public class SimpleRestController {

    private VerifyService verifyService;

    @Value("${secret.key}")
    private String SECRET;

    @Value("${google.api}")
    private String GOOGLE_API_URL;

    @Autowired
    public SimpleRestController(VerifyService verifyService){
        this.verifyService = verifyService;
    }

    public SimpleRestController(VerifyService verifyService, String SECRET, String GOOGLE_API_URL){
        this.verifyService = verifyService;
        this.SECRET = SECRET;
        this.GOOGLE_API_URL = GOOGLE_API_URL;
    }

    @PostMapping("/")
    public String postRequest(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("ReCatcha") String recatchaResponse) throws Exception {
        if(verifyService.verifyingUsersResponse(recatchaResponse, GOOGLE_API_URL, SECRET)){
            return this.fakeProcess(email,password);
        }
        throw new Exception();
    }

    private String fakeProcess(String email, String password){
        return "success";
    }


}

