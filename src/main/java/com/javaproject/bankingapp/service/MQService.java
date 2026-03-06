package com.javaproject.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MQService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String queueName, String message) {
        try {
            jmsTemplate.convertAndSend(queueName, message);
            System.out.println("✅ Message sent to queue: " + queueName + " → " + message);
        } catch (Exception e) {
            System.out.println("⚠️ MQ unavailable, message not sent: " + e.getMessage());
        }
    }
}