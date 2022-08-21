package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@CrossOrigin(origins="http://localhost:4200/",allowedHeaders="*")
@RestController
public class UserContoller {
	Logger log = LoggerFactory.getLogger(UserContoller.class);
	@Autowired
	private UserService service;
	
	
	// GET ALL USER
    @GetMapping("/GetAllUser")
    public  ResponseEntity<List<User>> getAllUser() {
        
        try{
            log.debug("Request {}");
            String response = "Inside the GetAllUser Function";
            log.debug("Response {}", response);
            //System.out.println("in the getAllUserFunction");
            List<User> listuser = service.listAll();
            
            return new ResponseEntity<>(listuser, HttpStatus.OK);
            }
        catch (Exception e) {
            log.debug("Response {}", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
 // CREATE USER
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            log.debug("Request {}");
            String response = "Inside the CreateUser Function";
            log.debug("Response {}", response);
            service.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
            
        } catch (Exception e) {
            log.debug("Request {}");
            String response = " createUser Function: CANNOT CREATE USER";
            log.debug("Response {}", response);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
	
 // GET USER By ID
    @GetMapping("getuser/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        try {
            log.debug("Request {}");
            String response = "Inside the GetUserByID UserdID: " + id + " Function";
            log.debug("Response {}", response);
            User user = service.get(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("Request {}");
            String response = " GetUserByID Function: UserID: " + id + " Not Present";
            log.debug("Response {}", response);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

   }
	
 // DELETE USER
    @DeleteMapping("deleteuser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id)
    {    System.out.println("in the Delete Function");
        //return service.delete(id);
        try {
            log.debug("Request {}");
            String response = "Inside the DeleteUser Function";
            log.debug("Response {}", response);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.debug("Response {}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
 // UPDATE USER
    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userDetails, @PathVariable Integer id) {
        // System.out.println("in the UpdateUser Function");
        try {
            log.debug("Request {}");
            String response = "Inside the updateUser Function";
            log.debug("Response {}", response);
            
            User user = service.get(id);
            user.setDob(userDetails.getDob());
            user.setEmailid(userDetails.getEmailid());
            user.setGender(userDetails.getGender());
            user.setMobile(userDetails.getMobile());
            user.setName(userDetails.getName());
            user.setSmobile(userDetails.getSmobile());
            User updatedUser = service.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            log.debug("Response {}", e);
            String response = " updateUser Function: UserID: " + id +" Not Present";
            log.debug("Response {}", response);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
   }
	
}
