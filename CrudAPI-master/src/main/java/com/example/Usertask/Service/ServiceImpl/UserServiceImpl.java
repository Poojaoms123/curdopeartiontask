package com.example.Usertask.Service.ServiceImpl;

import com.example.Usertask.Model.Response.pageDTO;
import com.example.Usertask.Model.SaveRequest.SaveUserRequest;
import com.example.Usertask.Model.User;
import com.example.Usertask.Repository.UserRepository;
import com.example.Usertask.Service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Object saveOrUpdate(SaveUserRequest saveUserRequest) throws Exception {

        if (userRepository.existsById(saveUserRequest.getId())) {
            List<Long> ids=new ArrayList<>();
            ids.add(saveUserRequest.getId());
            User user = userRepository.findById(saveUserRequest.getId()).get();
            user.setName(saveUserRequest.getName());
            if(userRepository.existsByEmailAndIdNotIn(saveUserRequest.getEmail(),ids)){
                throw new Exception("Email already exists");
            }else {
                user.setEmail(saveUserRequest.getEmail());
            }
            user.setPassword(saveUserRequest.getPassword());
            user.setCity(saveUserRequest.getCity());
            user.setState(saveUserRequest.getState());
            user.setCountry(saveUserRequest.getCountry());
            user.setZipcode(saveUserRequest.getZipcode());
            user.setIsDeleted(false);
            user.setIsActive(true);
            userRepository.save(user);

            return "Updated Successfully";
        } else {
            User user = new User();
            user.setName(saveUserRequest.getName());
            if (userRepository.existsByEmail(saveUserRequest.getEmail())) {
                throw new Exception("Email already exists");
            } else {
                user.setEmail(saveUserRequest.getEmail());
            }
            user.setPassword(saveUserRequest.getPassword());
            user.setCity(saveUserRequest.getCity());
            user.setState(saveUserRequest.getState());
            user.setCountry(saveUserRequest.getCountry());
            user.setZipcode(saveUserRequest.getZipcode());
            user.setIsDeleted(false);
            user.setIsActive(true);
            userRepository.save(user);

            return "Saved Successfully";
        }
    }



    @Override
    public Object getUser( String email, String name, String zipcode, Pageable pageable) {
        Page<User> user = null;
        if (StringUtils.isNotBlank(email) || StringUtils.isNotBlank(name) || StringUtils.isNotBlank(zipcode)) {
            if (StringUtils.isNotBlank(email)) {
                email = email.toLowerCase();
            } else {
                email = null;
            }
            if (StringUtils.isNotBlank(name)) {
                name = name.toLowerCase();
            } else {
                name = null;
            }
            if (StringUtils.isNotBlank(zipcode)) {
                zipcode = zipcode.toLowerCase();
            } else {
                zipcode = null;
            }
            user = userRepository.getUser( email, name, zipcode, pageable);
        } else {
            user = userRepository.getUserId(pageable);
        }
        return new pageDTO(user.getContent(), user.getTotalElements(), user.getNumber(), user.getTotalPages());
    }

    @Override
    public List<User> getAllUser() {
        List<User> user= userRepository.findAll();
        return  user;
    }

}
