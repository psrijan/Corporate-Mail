package com.srijan.springfundamentals.controller;


import com.srijan.springfundamentals.dto.request.client.AddClientRequest;
import com.srijan.springfundamentals.dto.request.client.UpdateClientRequest;
import com.srijan.springfundamentals.dto.response.ClientDetail;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
        return clientService.getClientList();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addNewClient(@RequestBody @Valid AddClientRequest clientRequest) {
        log.info("Entering New Client API...");
        return clientService.addNewClient(clientRequest);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse updateClient(@PathVariable("id") Long id, @RequestBody @Valid UpdateClientRequest updateClientRequest) {
        log.info("Update Client Request ");
        updateClientRequest.setId(id);
        return clientService.updateClient(updateClientRequest);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse deleteClient(@PathVariable("id") @Valid @NotNull Long clientId) {
        log.info("Delete Client Request...");
        return clientService.deleteClient(clientId);
    }
}
