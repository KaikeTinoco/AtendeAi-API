package com.project.app.main.controllers;

import com.project.app.main.dtos.ChatOverviewDto;
import com.project.app.main.entities.ChatOverview;
import com.project.app.main.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatController {
    private final ChatService service;

    @Autowired
    public ChatController(ChatService service) {
        this.service = service;
    }

    @PutMapping("/register")
    public ResponseEntity<ChatOverview> registerChat(@RequestBody ChatOverviewDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerChat(dto));
    }

    @GetMapping("/getOverviewByOwner")
    public ResponseEntity<List<ChatOverview>> listAllOverviewsByOwner(@RequestParam Long ownerId){
        return ResponseEntity.ok(service.listAllOverviewsByOwner(ownerId));
    }
}
