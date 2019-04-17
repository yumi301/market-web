package com.joole.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.joole.domain.User;
import com.joole.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// get user by id
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.getUser(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserbyName(@RequestParam(value="userName")String userName, @RequestParam(value="password")String password) {
        System.out.println("Fetching User with name " + userName);
        User user = userService.getUserbyName(userName);

        if (user == null) {
            System.out.println("User with name " + userName + " not found");
            user = userService.getUserbyEmail(userName);//check userEmail 
            if(user == null){
            	System.out.println("no userName or userEmail!");
            	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
        }
        if(password.equals(user.getPassword())){
        	return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        System.out.println("password error!");
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
	
	


}
