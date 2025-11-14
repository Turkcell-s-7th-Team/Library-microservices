package com.TurkcellTakim7.publisher_service.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TurkcellTakim7.publisher_service.application.commandHandlers.CreatePublisherCommandHandler;
import com.TurkcellTakim7.publisher_service.application.commandHandlers.DeletePublisherCommandHandler;
import com.TurkcellTakim7.publisher_service.application.commandHandlers.UpdatePublisherCommandHandler;
import com.TurkcellTakim7.publisher_service.application.commands.CreatePublisherCommand;
import com.TurkcellTakim7.publisher_service.application.commands.DeletePublisherCommand;
import com.TurkcellTakim7.publisher_service.application.commands.UpdatePublisherCommand;
import com.TurkcellTakim7.publisher_service.application.dto.CreatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.application.dto.PublisherResponse;
import com.TurkcellTakim7.publisher_service.application.dto.UpdatedPublisherResponse;
import com.TurkcellTakim7.publisher_service.application.queries.GetPublisherByIdQuery;
import com.TurkcellTakim7.publisher_service.application.queries.GetPublisherListQuery;
import com.TurkcellTakim7.publisher_service.application.queryHandlers.GetPublisherByIdQueryHandler;
import com.TurkcellTakim7.publisher_service.application.queryHandlers.GetPublisherListQueryHandler;

@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {

  private final CreatePublisherCommandHandler createPublisherCommandHandler;
  private final DeletePublisherCommandHandler deletePublisherCommandHandler;
  private final UpdatePublisherCommandHandler updatePublisherCommandHandler;
  private final GetPublisherByIdQueryHandler getPublisherByIdQueryHandler;
  private final GetPublisherListQueryHandler getPublisherListQueryHandler;

  public PublisherController(CreatePublisherCommandHandler createPublisherCommandHandler,
      DeletePublisherCommandHandler deletePublisherCommandHandler,
      UpdatePublisherCommandHandler updatePublisherCommandHandler,
      GetPublisherByIdQueryHandler getPublisherByIdQueryHandler,
      GetPublisherListQueryHandler getPublisherListQueryHandler) {
    this.createPublisherCommandHandler = createPublisherCommandHandler;
    this.deletePublisherCommandHandler = deletePublisherCommandHandler;
    this.updatePublisherCommandHandler = updatePublisherCommandHandler;
    this.getPublisherByIdQueryHandler = getPublisherByIdQueryHandler;
    this.getPublisherListQueryHandler = getPublisherListQueryHandler;
  }

  @GetMapping("/{id}")
  public PublisherResponse getPublisher(@PathVariable UUID id) {
    return getPublisherByIdQueryHandler.handle(new GetPublisherByIdQuery(id));
  }

  @GetMapping()
  public List<PublisherResponse> getPublisherList() {
    return getPublisherListQueryHandler.handle(new GetPublisherListQuery());
  }

  @PostMapping
  public CreatedPublisherResponse createPublisher(@RequestBody CreatePublisherCommand command) {
    return createPublisherCommandHandler.handle(command);
  }

  @PutMapping("/{id}")
  public UpdatedPublisherResponse updatePublisher(@PathVariable UUID id, @RequestBody UpdatePublisherCommand command) {
    UpdatePublisherCommand updatedCommand = new UpdatePublisherCommand(
        id,
        command.publisherName(),
        command.address());

    return updatePublisherCommandHandler.handle(updatedCommand);
  }

  @DeleteMapping("/{id}")
  public Void deletePublisher(@PathVariable UUID id) {
    deletePublisherCommandHandler.handle(new DeletePublisherCommand(id));
    return null;
  }
}
