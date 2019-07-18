package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service , Long> {

}
