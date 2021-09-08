/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collins.loan.controller;

import com.collins.loan.model.LoanModel;
import com.collins.loan.service.LoanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author KEN19283
 */
@RestController
@RequestMapping(value = "/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    @Autowired
    LoanService loanService;
    private final MediaType mediaType = MediaType.APPLICATION_JSON;

    @GetMapping()
    public List<LoanModel> list() {
        List<LoanModel> loanList = loanService.getAllLoans();

        return loanList;
    }

    @PostMapping("/create")
    public LoanModel create(@RequestBody LoanModel loanModel) {
        loanService.saveOrUpdate(loanModel);
        return loanModel;
    }

//    @PutMapping("edit/{id}")
//    public LoanModel editLoan(@PathVariable long id, LoanModel loan) {
//
//        loanService.saveOrUpdate(loan);
//
//        return loan;
//
//    }

    @PutMapping("edit/{id}")
    public LoanModel updateLoan(@PathVariable long id, @RequestBody LoanModel loan) {
        loanService.saveOrUpdate(loan);
    return loan;
    }

    @GetMapping("view/{id}")
    public LoanModel viewLoan(@PathVariable long id) {

        LoanModel personDetails = loanService.getLoanById(id);

        return personDetails;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable long id) {

        loanService.deleteLoan(id);
        return;

    }

}
