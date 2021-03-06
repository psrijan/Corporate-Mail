package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Client, Long> {
}
