package com.srijan.springfundamentals.controller;


import com.srijan.springfundamentals.dto.response.ClientDetail;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<ClientDetail> getClientList() {
//        return null;
//    }
//
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public GenericResponse addNewClient() {
//        return null;
//    }
//
//    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public GenericResponse updateClient() {
//        return null;
//    }
//
//    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public GenericResponse deleteClient() {
//        return  null;
//    }
}
