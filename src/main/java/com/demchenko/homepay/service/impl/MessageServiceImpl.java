package com.demchenko.homepay.service.impl;

import com.demchenko.homepay.entity.Message;
import com.demchenko.homepay.repository.MessageRepository;
import com.demchenko.homepay.service.MessageService;
import com.demchenko.homepay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public Message createNewMessage(Long userId1, Long userId2, String text) {
        Message message = new Message();
        message.setUser(userService.findUserById(userId1));
        message.setText(text);
        message.setUserAddress(userService.findUserById(userId2));
        log.info("Save message with userId: {}, userAddressId: {}, text: {}", userId1, userId2, text);
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages(Long userId1, Long userId2) {
        log.info("Find all messages by userId1: {}, userId2: {}", userId1, userId2);
        return messageRepository.findAllByUserAndUserAddress(userService.findUserById(userId1), userService.findUserById(userId2));
    }
}
