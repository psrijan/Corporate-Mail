package com.srijan.springfundamentals.mapper;

import com.srijan.springfundamentals.dto.request.client.AddClientRequest;
import com.srijan.springfundamentals.dto.request.client.UpdateClientRequest;
import com.srijan.springfundamentals.dto.response.ClientDetail;
import com.srijan.springfundamentals.entities.Client;
import com.srijan.springfundamentals.entities.Profile;
import com.srijan.springfundamentals.util.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static List<ClientDetail> mapToClientDetailList(List<Client> clientList){

        List<ClientDetail> clientDetails = clientList.stream().map(client -> {
            ClientDetail clientDetail = ObjectMapper.map(client , ClientDetail.class);
            clientDetail.setProfileId(client.getProfile().getId());
            clientDetail.setProfileName(client.getProfile().getName());
            return clientDetail;
        }).collect(Collectors.toList());

        return clientDetails;
    }

    public static Client mapToClient(AddClientRequest addClientRequest) {

        Client client = ObjectMapper.map(addClientRequest , Client.class);
        client.setProfile(new Profile(addClientRequest.getProfileId()));
        return client;

    }

    public static void mapToClient(UpdateClientRequest updateClientRequest , Client client) {
         ObjectMapper.mapExcludingNulls(updateClientRequest , client);
         client.setProfile(new Profile(updateClientRequest.getProfileId()));
    }
}
