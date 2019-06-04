package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser , Long> {


    @Query("select t from ApplicationUser  t where t.username = :username and t.active <> 'D'")
    Optional<ApplicationUser> findApplicationUserByUsername(String username);

    @Query("select t from ApplicationUser t where t.active <> 'D'")
    List<ApplicationUser> findApplicationUserList();

    @Query("select t from ApplicationUser  t where t.id = :id and t.active <> 'D'")
    Optional<ApplicationUser> findApplicationUserById(Long id);

//    @Query("select count(t) from ApplicationUser t where t.userGroup.id = :groupId")
//    long countUserWithUserGroup(@Param("groupId") Long groupId);
}
