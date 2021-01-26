package com.springboot.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.ApiResponse;
import com.springboot.model.User;
import com.springboot.model.UserDto;
import com.springboot.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
	
	Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<User> saveUser(@RequestBody UserDto user){
    	log.info("UserController:saveUser()::START");
         ApiResponse<User> response=new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
        log.info("UserController:saveUser()::END");
        return response;
         
    }

    @GetMapping
    public ApiResponse<List<User>> listUser(){
    	log.info("UserController:listUser()::START");
    	ApiResponse<List<User>> response= new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
    	 log.info("UserController:listUserr()::END");
         return response;
          
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }
    @GetMapping("/lasname/{lastName}")
    public  ApiResponse<User>  getUserByLastName(@PathVariable String lastName) {
    	userService.findByLastName(lastName);
    	return new ApiResponse<>(HttpStatus.OK.value(), "User lastName fetched successfully.",userService.findByLastName(lastName));
    }


}
