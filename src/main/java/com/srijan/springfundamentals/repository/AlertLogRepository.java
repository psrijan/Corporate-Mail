package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.AlertLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {

    @Query("select count(t) from AlertLog t where t.applicationUser.id = ?1 and t.friend.id = ?2 and t.year =  ?3 and t.alertType = ?4")
    long countWishLogByYear(Long appUserId, Long friendId, String year , String alertType);


    @Query("select count(t) from AlertLog t where t.applicationUser.id = ?1 and t.friend.id = ?2 and t.year =  ?3 and t.alertType = concat(?4 , '%')")
    long countFestivalWishLogByYear(Long appUserId, Long friendId, String year , String alertType);

    @Query("select t from AlertLog t where t.date between :startDate and :endDate and t.alertType like concat(:alertType , '%')")
    List<AlertLog> getFestivalAlertLogByDate(@Param("startDate") Date startDate , @Param("endDate") Date endDate , @Param("alertType") String alertType);


    @Query("select t from AlertLog t where t.date between :startDate and :endDate and t.alertType ='BIRTHDAY'")
    List<AlertLog> getBirthdayAlertLogByDate(@Param("startDate") Date startDate , @Param("endDate") Date endDate);

}

