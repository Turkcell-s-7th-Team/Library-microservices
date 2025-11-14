package com.TurkcellTakim7.publisher_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.commands.UpdatePublisherCommand;
import com.TurkcellTakim7.publisher_service.application.core.CommandHandler;
import com.TurkcellTakim7.publisher_service.application.dto.UpdatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.application.mapper.UpdatePublisherMapper;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.repositories.PublisherRepository;
import com.TurkcellTakim7.publisher_service.domain.services.PublisherDomainService;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

@Component
public class UpdatePublisherCommandHandler implements CommandHandler<UpdatePublisherCommand, UpdatedPublisherResponse> {

  private final PublisherDomainService publisherDomainService;
  private final UpdatePublisherMapper updatePublisherMapper;
  private final PublisherRepository publisherRepository;

  public UpdatePublisherCommandHandler(PublisherDomainService publisherDomainService,
      UpdatePublisherMapper updatePublisherMapper, PublisherRepository publisherRepository) {
    this.publisherDomainService = publisherDomainService;
    this.updatePublisherMapper = updatePublisherMapper;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public UpdatedPublisherResponse handle(UpdatePublisherCommand command) {
    PublisherId publisherId = new PublisherId(command.publisherId());
    Publisher updatedPublisher = publisherDomainService.updatePublisher(publisherId,
        new PublisherName(command.publisherName()), new Address(command.address()));
    updatedPublisher = publisherRepository.save(updatedPublisher);
    return updatePublisherMapper.toResponse(updatedPublisher);
  }

}
