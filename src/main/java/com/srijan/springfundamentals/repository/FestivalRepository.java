package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    @Query("select t from Festival t where trim(t.date) = :date")
    List<Festival> getFestivalbyDate(@PathParam("date") String date);

    Optional<Festival> getFestivalByCode(String code);

}
