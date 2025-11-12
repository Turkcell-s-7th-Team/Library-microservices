package com.TurkcellTakim7.publisher_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.commands.CreatePublisherCommand;
import com.TurkcellTakim7.publisher_service.application.core.CommandHandler;
import com.TurkcellTakim7.publisher_service.application.dto.CreatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.application.mapper.CreatePublisherMapper;
import com.TurkcellTakim7.publisher_service.domain.entities.Publisher;
import com.TurkcellTakim7.publisher_service.domain.repositories.PublisherRepository;
import com.TurkcellTakim7.publisher_service.domain.services.PublisherDomainService;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.Address;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherName;

@Component
public class CreatePublisherCommandHandler implements CommandHandler<CreatePublisherCommand, CreatedPublisherResponse> {

  private final CreatePublisherMapper createPublisherMapper;
  private final PublisherRepository publisherRepository;
  private final PublisherDomainService publisherDomainService;

  public CreatePublisherCommandHandler(CreatePublisherMapper createPublisherMapper,
      PublisherRepository publisherRepository,
      PublisherDomainService publisherDomainService) {
    this.createPublisherMapper = createPublisherMapper;
    this.publisherRepository = publisherRepository;
    this.publisherDomainService = publisherDomainService;
  }

  @Override
  public CreatedPublisherResponse handle(CreatePublisherCommand command) {
    Publisher publisher = publisherDomainService.createPublisher(new PublisherName(command.publisherName()),
        new Address(command.address()));
    publisher = publisherRepository.save(publisher);
    return createPublisherMapper.toResponse(publisher);
  }

}
