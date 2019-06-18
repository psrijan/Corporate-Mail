package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.FestivalGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalGroupRepository extends JpaRepository<FestivalGroup , Long> {
}
