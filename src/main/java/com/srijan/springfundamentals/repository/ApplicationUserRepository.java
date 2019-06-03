package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser , Long> {
}
