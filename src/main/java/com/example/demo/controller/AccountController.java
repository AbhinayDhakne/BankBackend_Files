package com.example.demo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.service.AccountService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/acc")
public class AccountController {
   
   Logger log = LoggerFactory.getLogger(UserContoller.class);
   
   @Autowired
   private AccountService service;

   @GetMapping("/GetAllAccount")
   public ResponseEntity<List<Account>> getAllAccount() {
       
       try{
           log.debug("Request {}");
           String response = "Inside the getAllAccount Function";
           log.debug("Response {}", response);
   
           List<Account> listacc = service.listAll();
           return new ResponseEntity<>(listacc, HttpStatus.OK);
           }
       catch (Exception e) {
           log.debug("Response {}", e);
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   
       
       //return listacc;
   }

   //CREATE ACCOUNT
   @PostMapping("/addAccount")
   public ResponseEntity<Account> createAccount( @RequestBody Account acc )
   {
       try {
           log.debug("Request {}");
           String response = "Inside the CreateAccount Function";
           log.debug("Response {}", response);
           service.save(acc);
           return new ResponseEntity<>(acc, HttpStatus.OK);
           
       } catch (Exception e) {
           log.debug("Request {}");
           String response = " createAccount Function: CANNOT CREATE ACCOUNT";
           log.debug("Response {}", response);
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       
       //service.save(acc);
   }
   
   
   @GetMapping("/getaccount/{id}")
   public ResponseEntity<Account> getById(@PathVariable long id)
   {
       try {
           log.debug("Request {}");
           String response = "Inside the GetAccountByID AccountID: " + id + " Function";
           log.debug("Response {}", response);
           Account accData= service.get(id);
           return new ResponseEntity<>(accData, HttpStatus.OK);
       } catch (Exception e) {
           log.debug("Request {}");
           String response = " GetAccountrByID Function: AccountID: " + id + " Not Present";
           log.debug("Response {}", response);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       
       //return service.get(id);
        
   }
   //DELETE ACCOUNT
   @DeleteMapping("/deleteaccount/{id}")
   public ResponseEntity<HttpStatus> deleteAccount(@PathVariable long id) {
       // return service.delete(id);
       try {
           log.debug("Request {}");
           String response = "Inside the DeleteAccount Function";
           log.debug("Response {}", response);
           service.delete(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       } catch (Exception e) {
           log.debug("Response {}", e);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   
   //UPDATE ACCOUNT
   @PutMapping("update/{id}")
   public ResponseEntity<Account> updateUser(@RequestBody Account accDetails, @PathVariable Integer id) {
       try {
           log.debug("Request {}");
           String response = "Inside the updateAccount Function";
           log.debug("Response {}", response);

           Account acc = service.get(id);

           acc.setAccid(accDetails.getAccid());
           acc.setBname(accDetails.getBname());
           acc.setAtype(accDetails.getAtype());
           acc.setAbalance(accDetails.getAbalance());
           Account updatedAccount = service.save(acc);
           return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
       } catch (Exception e) {
           log.debug("Response {}", e);
           String response = " updateAccount Function: AccountID: " + id + " Not Present";
           log.debug("Response {}", response);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

   }
   
   
   // GET ALL ACCOUNTBYID
   @GetMapping("/GetAllAccountByID/{id}")
   public ResponseEntity<List<Account>> getAccount(@PathVariable int id) {
       
        try {
               log.debug("Request {}");
               String response = "Inside the GetAllAccountByID Function";
               log.debug("Response {}", response);
               
               List<Account> listuser = service.listAll();
               List<Account> listacc=new ArrayList<Account>();
               for(Account a:listuser)
               {
                   if(a.getUserid()==id)
                   {
                       listacc.add(a);
                   }
               }
               return new ResponseEntity<>(listacc, HttpStatus.OK);
           }catch(Exception e)
           {   
               log.debug("Response {}", e);
               String response = " updateAccount Function: " + id + " Not Present";
               log.debug("Response {}", response);
               return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
           }
   
      // return listacc;
   }
}
