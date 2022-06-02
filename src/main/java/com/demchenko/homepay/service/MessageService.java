package com.demchenko.homepay.service;

import com.demchenko.homepay.entity.Message;

import java.util.List;

public interface MessageService {

    Message createNewMessage(Long userId1, Long userId2, String text);

    List<Message> getAllMessages(Long userId1, Long userId2);
}
