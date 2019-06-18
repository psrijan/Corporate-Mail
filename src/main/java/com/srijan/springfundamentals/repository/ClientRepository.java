package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select t from client t where t.active <> 'D' ")
    List<Client> listAllClients();

    @Query("select t from client t where t.active <>'D' and t.id= :id")
    Optional<Client> getClientById(@Param("id") Long id);



}
