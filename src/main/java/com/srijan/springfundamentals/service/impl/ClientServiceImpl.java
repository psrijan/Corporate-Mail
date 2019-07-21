package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.builder.ResponseBuilder;
import com.srijan.springfundamentals.dto.request.client.AddClientRequest;
import com.srijan.springfundamentals.dto.request.client.UpdateClientRequest;
import com.srijan.springfundamentals.dto.response.ClientDetail;
import com.srijan.springfundamentals.dto.response.GenericResponse;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.mapper.ClientMapper;
import com.srijan.springfundamentals.repository.ClientRepository;
import com.srijan.springfundamentals.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientDetail> getClientList() {
        List<Client> clientList = clientRepository.listAllClients();
        return ClientMapper.mapToClientDetailList(clientList);
    }

    @Override
    public GenericResponse addNewClient(AddClientRequest addClientRequest) {
        Client client = ClientMapper.mapToClient(addClientRequest);
        client.setActive('Y');
        clientRepository.save(client);
        return new GenericResponse.Builder(true , "Successfully added new client").build();
    }

    @Override
    public GenericResponse updateClient(UpdateClientRequest updateClientRequest) {
        Client client = clientRepository.getClientById(updateClientRequest.getId()).get();
        ClientMapper.mapToClient(updateClientRequest , client);
        clientRepository.save(client);
        return ResponseBuilder.buildSuccessResponse("Successfully Updated Client");
    }

    @Override
    public GenericResponse deleteClient(Long clientId) {
        Optional<Client> clientOpt = clientRepository.getClientById(clientId);
        Client client = clientOpt.get();
        client.setActive('D');
        clientRepository.save(client);
        return ResponseBuilder.buildSuccessResponse("Successfully Deleted Client with id " + clientId);
    }
}
