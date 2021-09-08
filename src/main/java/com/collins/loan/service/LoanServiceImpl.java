/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collins.loan.service;

import com.collins.loan.model.LoanModel;
import com.collins.loan.repository.LoanRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author KEN19283
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;
    public long outstandingAmount = 0;
    public double time = 0;

    @Override
    public List<LoanModel> getAllLoans() {

        // TODO Auto-generated method stub
        return (List<LoanModel>) loanRepository.findAll();

    }

    @Override
    public LoanModel getLoanById(long id) {
        return loanRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(LoanModel loanModel) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate str= LocalDate.now();
        str.format(formatter);
          int loanLimit = 0;
      

        if (loanLimit <= 15000) {
            loanModel.setAmount_due(loanModel.getAmount_requested() + (int) (0.10 * loanModel.getAmount_requested()));
            loanModel.setDate(str.toString());
            loanModel.setDuedate(str.plusDays(15).toString());
            loanRepository.save(loanModel);

        }
        if (loanLimit <= 25000 && loanLimit > 15000) {
            loanModel.setAmount_due(loanModel.getAmount_requested() + (int) (0.125 * loanModel.getAmount_requested()));
            loanModel.setDate(str.toString());
            loanModel.setDuedate(str.plusDays(25).toString());
            loanRepository.save(loanModel);

        }
        loanRepository.save(loanModel);

    }

    @Override
    public void deleteLoan(long id) {
        loanRepository.deleteById(id);
    }

}
