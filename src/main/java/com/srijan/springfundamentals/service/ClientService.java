package com.srijan.springfundamentals.service;

import com.srijan.springfundamentals.dto.request.client.AddClientRequest;
import com.srijan.springfundamentals.dto.request.client.UpdateClientRequest;
import com.srijan.springfundamentals.dto.response.ClientDetail;
import com.srijan.springfundamentals.dto.response.GenericResponse;

import java.util.List;

public interface ClientService {

    List<ClientDetail> getClientList();

    GenericResponse addNewClient(AddClientRequest addClientRequest);

    GenericResponse updateClient(UpdateClientRequest updateClientRequest);

    GenericResponse deleteClient(Long clientId);


}
