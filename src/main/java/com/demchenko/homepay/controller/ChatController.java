package com.demchenko.homepay.controller;

import com.demchenko.homepay.dto.response.MessageResponseText;
import com.demchenko.homepay.mapper.MessageMapper;
import com.demchenko.homepay.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class ChatController {

    private final MessageService messageService;

    private final MessageMapper messageMapper;

    @Autowired
    public ChatController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<MessageResponseText> createNewMessage(@Positive Long userId1, @Positive Long userId2, @NotBlank String text) {
        return new ResponseEntity<>(messageMapper.messageToMessageResponseText(messageService.createNewMessage(userId1, userId2, text)), HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<List<MessageResponseText>> getMessages(@Positive Long userId1, @Positive Long userId2) {
        return new ResponseEntity<>(messageService.getAllMessages(userId1, userId2).stream()
                .map(messageMapper :: messageToMessageResponseText).collect(Collectors.toList()), HttpStatus.OK);
    }
}
