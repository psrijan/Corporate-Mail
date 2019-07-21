package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select t from Client t where t.active <> 'D' ")
    List<Client> listAllClients();

    @Query("select t from Client t where t.active <>'D' and t.id= :id")
    Optional<Client> getClientById(@Param("id") Long id);

//    @Query("select t from Client t where IFNULL(DATE_FORMAT(t.birthday, '%m-%d'), ' ')  between  IFNULL(DATE_FORMAT(?1 , '%m-%d '), ' ') and  IFNULL(DATE_FORMAT(?2, '%m-%d '), ' ') and t.active <> 'D'")
    @Query("SELECT t FROM Client t WHERE DATE_FORMAT(t.birthday,  '%m-%d')  BETWEEN DATE_FORMAT(?1,'%m-%d') AND DATE_FORMAT (?2,'%m-%d')")
    List<Client> clientsWithBirthdayBetween(Date startDate, Date endDate);

}
