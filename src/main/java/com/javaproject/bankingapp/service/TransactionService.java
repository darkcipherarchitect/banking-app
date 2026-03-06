package com.javaproject.bankingapp.service;

import com.javaproject.bankingapp.model.Transaction;
import com.javaproject.bankingapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MQService mqService;

    public Transaction createTransaction(Transaction transaction) {
        Transaction saved = transactionRepository.save(transaction);
        mqService.sendMessage("TRANSACTION.QUEUE",
                "Transaction created: " + saved.getId() +
                        " | Account: " + saved.getAccountNumber() +
                        " | Amount: " + saved.getAmount());
        return saved;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}