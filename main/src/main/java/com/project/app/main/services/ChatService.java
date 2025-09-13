package com.project.app.main.services;

import com.project.app.main.dtos.ChatOverviewDto;
import com.project.app.main.entities.ChatOverview;
import com.project.app.main.entities.Owner;
import com.project.app.main.exceptions.BadRequestException;
import com.project.app.main.exceptions.EmptyListException;
import com.project.app.main.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository repository;
    private final OwnerService ownerService;

    @Autowired
    public ChatService(ChatRepository repository, OwnerService ownerService) {
        this.repository = repository;
        this.ownerService = ownerService;
    }

    public ChatOverview registerChat(ChatOverviewDto dto){
        if(dto.getOverview() == null || dto.getRating() == null
        || dto.getOwnerId() == null || dto.getNextSugestedAction() == null){
            throw new BadRequestException("Não foi possível criar um registro com parâmetros vazios");
        }
        Owner owner = ownerService.findOwnerById(dto.getOwnerId());

        ChatOverview newChat = ChatOverview.builder()
                .owner(owner)
                .rating(dto.getRating())
                .nextSugestedAction(dto.getNextSugestedAction())
                .overview(dto.getOverview())
                .build();
        repository.save(newChat);
        ownerService.saveOwner(owner);
        return newChat;
    }

    public List<ChatOverview> listAllOverviewsByOwner(Long ownerId){
        if (ownerId == null){
            throw new BadRequestException("Por favor, informe o Id do usuário para a busca");
        }
        Owner owner = ownerService.findOwnerById(ownerId);
        List<ChatOverview> userOverviews = owner.getChatOverviews();
        if (userOverviews.isEmpty()){
            throw new EmptyListException("A lista de conversa desse usuário está vazia");
        }
        return userOverviews;
    }

}
