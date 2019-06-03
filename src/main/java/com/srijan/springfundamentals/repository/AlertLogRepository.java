package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.AlertLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {

    @Query("select count(t) from AlertLog t where t.applicationUser.id = ?1 and t.friend.id = ?2 and t.year =  ?3 and t.name = ?4")
    long countWishLogByYear(Long appUserId, Long friendId, String year , String name);



}

