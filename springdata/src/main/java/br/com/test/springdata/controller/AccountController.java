package br.com.test.springdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.test.springdata.entity.Account;
import br.com.test.springdata.repository.AccountRepository;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountRepository accountRepo;
	
    @RequestMapping("/list")
    public List<Account> findAll(){
        return accountRepo.findAll();
    }
    
    @RequestMapping("/findbylastname")
    public List<Account> fetchDataByLastName(@RequestParam("name") String name){
                 
        return accountRepo.findByName(name);
    }
}