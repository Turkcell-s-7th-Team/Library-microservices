package com.TurkcellTakim7.publisher_service.infrastructure.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class JpaPublisherEntity {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Embedded
  private String address;

  public JpaPublisherEntity() {
  }

  public JpaPublisherEntity(UUID id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
