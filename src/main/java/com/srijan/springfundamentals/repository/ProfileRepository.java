package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile , Long> {

    @Query("select t from Profile t where t.active <> 'D' ")
    List<Profile> getAllProfile();


}
