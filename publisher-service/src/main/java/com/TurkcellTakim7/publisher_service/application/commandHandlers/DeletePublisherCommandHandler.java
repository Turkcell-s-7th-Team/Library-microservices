package com.TurkcellTakim7.publisher_service.application.commandHandlers;

import org.springframework.stereotype.Component;

import com.TurkcellTakim7.publisher_service.application.commands.DeletePublisherCommand;
import com.TurkcellTakim7.publisher_service.application.core.CommandHandler;
import com.TurkcellTakim7.publisher_service.domain.services.PublisherDomainService;
import com.TurkcellTakim7.publisher_service.domain.valueobjects.PublisherId;
@Component
public class DeletePublisherCommandHandler implements CommandHandler<DeletePublisherCommand, Void> {
  private final PublisherDomainService publisherDomainService;

  public DeletePublisherCommandHandler(PublisherDomainService publisherDomainService) {
    this.publisherDomainService = publisherDomainService;
  }

  @Override
  public Void handle(DeletePublisherCommand command) {
    PublisherId publisherId = new PublisherId(command.id());
    publisherDomainService.deletePublisher(publisherId);
    return null;
  }

}
