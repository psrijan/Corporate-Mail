package com.srijan.springfundamentals.controller;


import com.srijan.springfundamentals.dto.response.ClientDetail;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDetail> getClientList() {
        log.info("Entering Client List API...");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addNewClient() {
        log.info("Entering New Client API...");
        return null;
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse updateClient() {
        return null;
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse deleteClient() {
        return  null;
    }
}
