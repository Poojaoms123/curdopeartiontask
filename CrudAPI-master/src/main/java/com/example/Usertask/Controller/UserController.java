package com.example.Usertask.Controller;

import com.example.Usertask.Model.Response.EntityResponse;
import com.example.Usertask.Model.SaveRequest.SaveUserRequest;
import com.example.Usertask.Model.User;
import com.example.Usertask.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    @GetMapping("/firstapi")
    public String firstapi(){
        return "working";
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?>saveOrUpdate(@Valid @RequestBody SaveUserRequest saveUserRequest){
        try {
            return new ResponseEntity<>(new EntityResponse(userService.saveOrUpdate(saveUserRequest),0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new EntityResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }


    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(
                                     @RequestParam(defaultValue = "0",required = false)Integer pageNo,
                                     @RequestParam(defaultValue = "30",required = false)Integer pageSize,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false)String name,
                                     @RequestParam(required = false)String zipcode) {
        try {
            Pageable pageable = PageRequest.of(pageNo,pageSize);
            return new ResponseEntity<>(new EntityResponse(userService.getUser(email,name,zipcode,pageable),0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    private List<User>getAllStudent(){
        return userService.getAllUser();
    }

}
