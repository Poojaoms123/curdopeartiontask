package com.example.Usertask.Service;

import com.example.Usertask.Model.SaveRequest.SaveUserRequest;
import com.example.Usertask.Model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Object saveOrUpdate(SaveUserRequest saveUserRequest) throws Exception;



    Object getUser( String email, String name, String zipcode, Pageable pageable);


    List<User> getAllUser();
}
