package com.example.Usertask.Repository;

import com.example.Usertask.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    @Query(value = "select u from User as u where (:email is null or lower(u.email) like %:email%) and (:name is null or lower(u.name) like %:name%) and (:zipcode is null or lower(u.zipcode)like %:zipcode%) and u.isDeleted=false ")
    Page<User> getUser(String email, String name, String zipcode, Pageable pageable);

    @Query(value = "select * from User_usertask where isDeleted=false ",nativeQuery = true)
    Page<User> getUserId(Pageable pageable);

    boolean existsByEmailAndIdNotIn(String email, List<Long> ids);

}
