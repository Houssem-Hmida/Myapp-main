//package com.example.myapp.presentation.controller;
//
//import com.example.myapp.persistence.model.Message;
//import com.example.myapp.persistence.repository.MessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//import java.util.Date;
//
//@Controller
//public class ChatController {
//
//    @Autowired
//    private SimpMessagingTemplate template;
//
//    @Autowired
//    private MessageRepository messageRepository;
//
//    /**
//     * Sends a message to its destination channel
//     *
//     * @param message
//     */
//    @MessageMapping("/messages")
//    public void handleMessage(Message message) {
//        message.setTimestamp(new Date());
//        messageRepository.save(message);
//        template.convertAndSend("/channel/chat/" + message.getChannel(), message);
//    }
//}